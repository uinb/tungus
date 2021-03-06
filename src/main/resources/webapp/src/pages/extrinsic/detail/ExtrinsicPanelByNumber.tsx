/**
 * @license
 * Copyright 2021 UINB Technologies Pte. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import React, { useMemo } from 'react';
import { useIntl } from 'umi';
import {
  RightOutlined,
  DownOutlined,
  QuestionCircleOutlined,
} from '@ant-design/icons';
import { Tabs, Table, Tooltip } from 'antd';
import Nodata from '@/components/Nodata';
import Parameters from '@/components/Parameters';
import CopyText from '@/components/CopyText';

import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';

import { formatTime, formatHash } from '@/utils/commonUtils';
import { SignedBlock } from '@polkadot/types/interfaces/runtime';
import useFinalizedBlock from '@/hooks/useFinalizedBlock';

dayjs.extend(utc);
const { TabPane } = Tabs;
const PendingIcon = require('@/assets/pending.svg');
const SuccessIcon = require('@/assets/successful.svg');
const FailedIcon = require('@/assets/failed.svg');

const ExtrinsicDetail: React.FC<{
  extrinsicId: string;
  block: SignedBlock;
  blockNumber: string;
  extrinsic: ChainTypes.IExtrinsic;
  extrinsicState: any;
  eventData: ChainTypes.IEvent[] | undefined;
}> = ({ extrinsic, extrinsicState, block, eventData, blockNumber }) => {
  const intl = useIntl();
  const finalizedBlock = useFinalizedBlock();
  const destination = useMemo(() => {
    if (
      extrinsic.method === 'transfer' ||
      extrinsic.method === 'transferKeepAlive'
    ) {
      let result;
      if (eventData) {
        eventData.forEach((event) => {
          if (event.action === 'balances(Transfer)')
            result = (event.data.toHuman() as any)[1];
        });
      }
      return result;
    } else {
      return '';
    }
  }, [extrinsic, eventData]);
  const blockTimeInfo = useMemo(() => {
    if (block) {
      const timeStr = block.block.extrinsics[0]?.args[0]?.toString();
      const timeStamp = +timeStr.split(',').join('');
      const spaceTime = Date.now() - timeStamp;
      return {
        timeStamp: dayjs(timeStamp).utc().format('YYYY-MM-DD HH:mm:ss'),
        blockTime: formatTime(spaceTime),
      };
    } else {
      return {
        timeStamp: '',
        blockTime: '',
      };
    }
  }, [block]);
  const blockState = useMemo(() => {
    if (finalizedBlock && block) {
      let res = block.block.header.number.toNumber();
      return finalizedBlock.number.toNumber() > res;
    } else {
      return false;
    }
  }, [block, finalizedBlock]);
  const eventsColumns = [
    {
      title: intl.formatMessage({
        id: 'eventId',
      }),
      dataIndex: 'id',
    },
    {
      title: intl.formatMessage({
        id: 'hash',
      }),
      dataIndex: 'hash',
      render: (text: string) => (
        <Tooltip title={text} color="#474747">
          <span>{formatHash(text)}</span>
        </Tooltip>
      ),
    },
    {
      title: intl.formatMessage({
        id: 'action',
      }),
      dataIndex: 'action',
    },
  ];
  return (
    <>
      {block && extrinsic ? (
        <>
          <section className="base-card">
            <ul className="block-info">
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'timestamp',
                  })}
                </h4>
                <span>{blockTimeInfo.timeStamp} (+UTC)</span>
              </li>
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'block',
                  })}
                </h4>
                <img
                  src={blockState ? SuccessIcon : PendingIcon}
                  alt=""
                  style={{ marginRight: '8px' }}
                />
                <span>{block.block.header.hash.toString()}</span>
              </li>
              {extrinsic.era ? (
                <li>
                  <h4>
                    {intl.formatMessage({
                      id: 'lifetime',
                    })}
                  </h4>
                  {extrinsic.era.isMortalEra ? (
                    <span>
                      {extrinsic.era.asMortalEra.birth(+blockNumber) +
                        ' - ' +
                        extrinsic.era.asMortalEra.death(+blockNumber)}
                    </span>
                  ) : (
                    'Immortal'
                  )}
                </li>
              ) : (
                ''
              )}
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'callableHash',
                  })}
                </h4>
                <CopyText text={extrinsic.hash} />
              </li>
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'module',
                  })}
                </h4>
                <span>{extrinsic.section}</span>
              </li>
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'call',
                  })}
                </h4>
                <span>{extrinsic.method}</span>
              </li>
              {extrinsic.signer &&
              (extrinsic.method === 'transfer' ||
                extrinsic.method === 'transferKeepAlive') ? (
                <li>
                  <h4>
                    {intl.formatMessage({
                      id: 'origin',
                    })}
                  </h4>
                  <CopyText text={extrinsic.signer} />
                </li>
              ) : null}
              {destination ? (
                <li>
                  <h4>
                    {intl.formatMessage({
                      id: 'destination',
                    })}
                  </h4>
                  <span>{destination}</span>
                </li>
              ) : null}

              {extrinsicState.fee && (
                <li>
                  <h4>Fee</h4>
                  <span>{extrinsicState.fee}</span>
                </li>
              )}
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'nonce',
                  })}
                </h4>
                <span>{extrinsic.nonce}</span>
              </li>
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'result',
                  })}
                </h4>
                <img
                  src={
                    extrinsicState.result === 'ExtrinsicSuccess'
                      ? SuccessIcon
                      : FailedIcon
                  }
                  style={{
                    marginRight: '8px',
                  }}
                  alt=""
                />
                {extrinsicState.reason ? (
                  <span>
                    {extrinsicState.result === 'ExtrinsicSuccess'
                      ? 'Success'
                      : `Fail ( ${extrinsicState.reason.name} )`}
                    {extrinsicState.result === 'ExtrinsicFailed' && (
                      <Tooltip
                        title={extrinsicState.reason.documentation.join('')}
                        color="#474747"
                      >
                        <QuestionCircleOutlined
                          style={{
                            marginLeft: '8px',
                          }}
                        />
                      </Tooltip>
                    )}
                  </span>
                ) : (
                  ''
                )}
              </li>
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'parameters',
                  })}
                </h4>
                <div>
                  <Parameters extrinsic={extrinsic} className="with-padding" />
                </div>
              </li>
              {extrinsic.signature && destination && (
                <li>
                  <h4>
                    {intl.formatMessage({
                      id: 'signature',
                    })}
                  </h4>
                  <div className="with-padding">
                    <span>{extrinsic.signature}</span>
                  </div>
                </li>
              )}
            </ul>
          </section>
          <section className="base-card">
            <Tabs className="user-tab">
              <TabPane tab={`Events (${eventData?.length})`} key="events">
                <Table
                  className="user-table f14"
                  columns={eventsColumns}
                  dataSource={eventData}
                  expandable={{
                    expandedRowRender: (row) => <Parameters event={row} />,
                    expandIcon: ({ expanded, record, onExpand }) => (
                      <span
                        className="expand-button"
                        onClick={(e) => onExpand(record, e)}
                      >
                        {expanded ? <DownOutlined /> : <RightOutlined />}
                      </span>
                    ),
                    expandIconColumnIndex: 5,
                  }}
                  pagination={false}
                />
              </TabPane>
            </Tabs>
          </section>
        </>
      ) : (
        <Nodata />
      )}
    </>
  );
};

export default ExtrinsicDetail;

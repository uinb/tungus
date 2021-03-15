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

import React, { useMemo, useContext } from 'react';
import { Tabs, Table, Tooltip } from 'antd';
import CopyText from '@/components/CopyText';
import Parameters from '@/components/Parameters';
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';
import { RightOutlined, DownOutlined } from '@ant-design/icons';
import { useIntl } from 'umi';

import FinalizedContext from '@/context/FinalizedContext';
import { extractExtrinsic, extractEventsWithFilter } from '@/utils/chainUtils';
import { formatHash } from '@/utils/commonUtils';

import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
dayjs.extend(utc);
const { TabPane } = Tabs;
const PendingIcon = require('@/assets/pending.svg');
const SuccessIcon = require('@/assets/successful.svg');
const FailedIcon = require('@/assets/failed.svg');
interface IBlockPanel {
  block: ISignedBlock;
  blockNumber: number;
  eventRecord: IEventRecord;
}
const BlockPanel: React.FC<IBlockPanel> = React.memo(
  ({ block, blockNumber, eventRecord }) => {
    const intl = useIntl();
    const eventData = extractEventsWithFilter(eventRecord, blockNumber);
    const extrinsicData = extractExtrinsic(
      block.block.extrinsics,
      blockNumber,
      eventData,
    );
    const finalizedHeader = useContext(FinalizedContext);
    const blockTimeInfo = useMemo(() => {
      if (block) {
        const timeStr = block.block.extrinsics[0]?.args[0]?.toString();
        const timeStamp = +timeStr.split(',').join('');
        return {
          timeStamp: dayjs(timeStamp).utc().format('YYYY-MM-DD HH:mm:ss'),
          blockTime: timeStamp,
        };
      }
    }, [block]);
    const blockState = useMemo(() => {
      if (finalizedHeader && blockNumber) {
        return finalizedHeader.number.toNumber() > blockNumber;
      } else {
        return false;
      }
    }, [blockNumber, finalizedHeader]);
    const callableColumns = [
      {
        title: intl.formatMessage({ id: 'callableId' }),
        dataIndex: 'id',
        render: (text: string) => {
          return <ShortLink hash={text} text={text} path="/callable" />;
        },
      },
      {
        title: intl.formatMessage({ id: 'hash' }),
        dataIndex: 'hash',
        render: (text: string) => (
          <Tooltip title={text} color="#474747">
            <span>{formatHash(text)}</span>
          </Tooltip>
        ),
      },
      {
        title: intl.formatMessage({
          id: 'time',
        }),
        dataIndex: 'time',
        render: () =>
          blockTimeInfo ? <Time time={+blockTimeInfo.blockTime} /> : null,
      },
      {
        title: intl.formatMessage({
          id: 'result',
        }),
        dataIndex: 'result',
        render: (text: string) => (
          <img
            src={text === 'ExtrinsicSuccess' ? SuccessIcon : FailedIcon}
            alt=""
          />
        ),
      },
      {
        title: intl.formatMessage({
          id: 'action',
        }),
        dataIndex: 'action',
      },
    ];
    const eventsColumns = [
      {
        title: intl.formatMessage({ id: 'eventId' }),
        dataIndex: 'id',
      },
      {
        title: intl.formatMessage({ id: 'hash' }),
        dataIndex: 'hash',
        render: (text: string) => (
          <Tooltip title={text} color="#474747">
            <span>{formatHash(text)}</span>
          </Tooltip>
        ),
      },
      {
        title: intl.formatMessage({ id: 'action' }),
        dataIndex: 'action',
      },
    ];
    const { header } = block.block;
    return (
      <>
        <section className="base-card">
          <ul className="block-info">
            <li>
              <h4>{intl.formatMessage({ id: 'timestamp' })}</h4>
              {blockTimeInfo ? (
                <span>{blockTimeInfo.timeStamp} (+UTC)</span>
              ) : null}
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'status' })}</h4>
              <span>
                <img
                  src={blockState ? SuccessIcon : PendingIcon}
                  alt=""
                  style={{ marginRight: '8px' }}
                />
                <span>
                  {blockState
                    ? intl.formatMessage({ id: 'finalized' })
                    : intl.formatMessage({ id: 'unfinalized' })}
                </span>
              </span>
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'hash' })}</h4>
              <CopyText text={header.hash.toHex()} />
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'parentHash' })}</h4>
              <span>{header.parentHash.toHex()}</span>
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'stateRoot' })}</h4>
              <span>{header.stateRoot.toHex()}</span>
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'extrinsicRoot' })}</h4>
              <span>{header.extrinsicsRoot.toHex()}</span>
            </li>
            <li>
              <h4>{intl.formatMessage({ id: 'blockTime' })}</h4>
              {blockTimeInfo ? <Time time={+blockTimeInfo.blockTime} /> : null}
            </li>
          </ul>
        </section>
        <section className="base-card">
          <Tabs className="user-tab">
            <TabPane
              tab={`${intl.formatMessage({ id: 'callables' })} (${
                extrinsicData?.length
              })`}
              key="callable"
            >
              <Table
                className="user-table f14"
                columns={callableColumns}
                dataSource={extrinsicData}
                expandable={{
                  expandedRowRender: (row) => <Parameters extrinsic={row} />,
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
            <TabPane
              tab={`${intl.formatMessage({ id: 'events' })} (${
                eventData?.length
              })`}
              key="events"
            >
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
    );
  },
);
export default BlockPanel;

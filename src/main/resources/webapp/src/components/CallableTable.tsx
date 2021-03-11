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

import React from 'react';
import { Table, Tooltip } from 'antd';
import Parameters from '@/components/Parameters';
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';
import { RightOutlined, DownOutlined } from '@ant-design/icons';

import { GenericExtrinsic } from '@polkadot/types/extrinsic/Extrinsic';
import EventRecord from '@/resultTypes/EventRecord';
import { useIntl } from 'umi';
import { formatHash } from '@/utils/commonUtils';
import type { ApiPromise } from '@polkadot/api';

const SuccessIcon = require('@/assets/successful.svg');
const FailedIcon = require('@/assets/failed.svg');
interface IProps {
  api: ApiPromise;
  dataList: {
    ext: string;
    events: string;
    ext1st: string;
    block: number;
    extIndex: string;
  }[];
}

const CallableTable: React.FC<IProps> = ({ dataList, api }) => {
  const intl = useIntl();
  const registry = api.registry;
  const decodeList: ChainTypes.IExtrinsic[] = dataList.map((item) => {
    const extrinsic = new GenericExtrinsic(registry, item.ext);
    const timestampExtrinsic = new GenericExtrinsic(registry, item.ext1st);
    const eventRecord = new EventRecord(registry, item.events);
    const eventsResult = eventRecord.filter((record) => {
      return api.events.system.ExtrinsicFailed.is(record.event);
    });
    return {
      id: item.extIndex,
      key: item.extIndex,
      hash: extrinsic.hash.toString(),
      result: eventsResult.length > 0 ? 'ExtrinsicFailed' : 'ExtrinsicSuccess',
      action: `${extrinsic.method.section}(${extrinsic.method.method})`,
      section: extrinsic.method.section,
      method: extrinsic.method.method,
      metaArgs: extrinsic.meta.args,
      methodArgs: extrinsic.method.args,
      time: timestampExtrinsic.args[0].toString(),
    };
  });
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
      render: (time: string) => <Time time={+time} />,
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
  return (
    <Table
      className="user-table f14"
      columns={callableColumns}
      dataSource={decodeList}
      expandable={{
        expandedRowRender: (row) => <Parameters extrinsic={row} />,
        expandIcon: ({ expanded, record, onExpand }) => (
          <span className="expand-button" onClick={(e) => onExpand(record, e)}>
            {expanded ? <DownOutlined /> : <RightOutlined />}
          </span>
        ),
        expandIconColumnIndex: 5,
      }}
      pagination={false}
    />
  );
};
export default React.memo(CallableTable);

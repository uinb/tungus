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
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';

import { GenericExtrinsic } from '@polkadot/types/extrinsic/Extrinsic';
import EventRecord from '@/resultTypes/EventRecord';
import { useIntl } from 'umi';
import { formatHash } from '@/utils/commonUtils';
import type { ApiPromise } from '@polkadot/api';
import type { AnyJson } from '@polkadot/types/types';
const SuccessIcon = require('@/assets/successful.svg');
const FailedIcon = require('@/assets/failed.svg');
const ToIcon = require('@/assets/to.svg');

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
interface ITransfer extends ChainTypes.IExtrinsic {
  block: string;
  from: string;
  to: string;
  amount: AnyJson;
}
const TransferTable: React.FC<IProps> = ({ dataList, api }) => {
  const intl = useIntl();
  const registry = api.registry;
  const decodeList: ITransfer[] = dataList.map((item) => {
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
      block: item.block + '',
      from: (extrinsic.signer.toHuman() as any).Id,
      to: (extrinsic.args[0].toHuman() as any).Id,
      amount: extrinsic.args[1].toHuman(),
    };
  });
  const transferColumns = [
    {
      title: `${intl.formatMessage({
        id: 'callable',
      })} ID`,
      dataIndex: 'id',
      render: (text: string) => {
        return <ShortLink isUnderline={true} text={text} path="/callable" />;
      },
      width: 120,
    },
    {
      title: intl.formatMessage({
        id: 'block',
      }),
      dataIndex: 'block',
      render: (text: string) => {
        return <ShortLink isUnderline={true} text={text} path="/block" />;
      },
      width: 100,
    },
    {
      title: intl.formatMessage({
        id: 'time',
      }),
      dataIndex: 'time',
      render: (time: string) => <Time time={+time} />,
      width: 180,
    },
    {
      title: intl.formatMessage({
        id: 'from',
      }),
      dataIndex: 'from',
      render: (text: string) => {
        return <ShortLink isUnderline={true} hash={text} path="/account" />;
      },
      width: 150,
    },
    {
      title: '',
      dataIndex: 'icon',
      render: () => <img src={ToIcon} alt="" />,
      width: 60,
    },
    {
      title: intl.formatMessage({
        id: 'to',
      }),
      dataIndex: 'to',
      render: (text: string) => {
        return <ShortLink isUnderline={true} hash={text} path="/account" />;
      },
      width: 150,
    },
    {
      title: intl.formatMessage({
        id: 'amount',
      }),
      dataIndex: 'amount',
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
      width: 80,
    },
    {
      title: `${intl.formatMessage({
        id: 'hash',
      })}`,
      dataIndex: 'hash',
      render: (text: string) => {
        return <ShortLink hash={text} path="/callable" />;
      },
    },
  ];
  return (
    <Table
      className="user-table f14"
      columns={transferColumns}
      dataSource={decodeList}
      pagination={false}
    />
  );
};
export default React.memo(TransferTable);

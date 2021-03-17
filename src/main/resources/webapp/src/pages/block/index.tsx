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

import React, { useEffect, useState } from 'react';
import BaseTable from '@/components/BaseTable';
import BaseSearch from '@/components/BaseSearch';
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';
import { getRecords } from '@/api/api';
import { useIntl } from 'umi';
import './index.less';
const SuccessIcon = require('@/assets/successful.svg');
interface IBlockList {
  blkId: number;
  createTime: number;
  eventsCnt: number;
  extrinsicsCnt: number;
  number: number;
  hash: string;
}
const defaultPagination: IPagination = {
  size: 10,
  total: 1,
};
const Block: React.FC = () => {
  const intl = useIntl();
  const [loading, setLoading] = useState<boolean>(false);
  const [blockData, setBlockData] = useState<IBlockList[]>();
  const [pagination, setPagination] = useState<IPagination>(defaultPagination);
  const columns = [
    {
      title: intl.formatMessage({
        id: 'block',
      }),
      dataIndex: 'number',
      width: 200,
    },
    {
      title: intl.formatMessage({
        id: 'status',
      }),
      dataIndex: 'status',
      width: 150,
      render: () => <img src={SuccessIcon} alt="" />,
    },
    {
      title: intl.formatMessage({
        id: 'time',
      }),
      dataIndex: 'createTime',
      width: 200,
      render: (text: number) => <Time time={text} />,
    },
    {
      title: intl.formatMessage({
        id: 'callables',
      }),
      dataIndex: 'extrinsicsCnt',
      width: 120,
    },
    {
      title: intl.formatMessage({
        id: 'events',
      }),
      dataIndex: 'eventsCnt',
      width: 120,
    },
    {
      title: intl.formatMessage({
        id: 'blockHash',
      }),
      dataIndex: 'hash',
      render: (text: string) => <ShortLink hash={text} path="/block" />,
    },
  ];
  const onPageChange = (page: number, pageSize = 16) => {
    getDataList(page, pageSize);
  };
  const getDataList = (page: number, size: number) => {
    setLoading(true);
    getRecords('block', {
      page,
      size,
    })
      .then((res) => {
        setBlockData(res.data.list);
        setPagination((preState) => ({ ...preState, total: res.data.total }));
      })
      .finally(() => {
        setLoading(false);
      });
  };
  useEffect(() => {
    getDataList(1, 10);
  }, []);
  return (
    <div className="chain-detail base-container">
      <BaseSearch />
      {blockData ? (
        <BaseTable
          onPageChange={onPageChange}
          loading={loading}
          columns={columns}
          dataSource={blockData}
          rowKey="blkId"
          pagination={pagination}
        />
      ) : null}
    </div>
  );
};
export default React.memo(Block);

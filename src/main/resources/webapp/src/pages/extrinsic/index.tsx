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

import React, { useEffect, useMemo, useState } from 'react';
import BaseTable from '@/components/BaseTable';
import BaseSearch from '@/components/BaseSearch';
import CallableTable from '@/components/CallableTable';
import TransferTable from '@/components/TransferTable';
import Time from '@/components/FormatTime';
import ShortLink from '@/components/ShortLink';
import { useLocation, useIntl } from 'umi';
import { useApi } from '@/context/ApiContext';
import { getRecords } from '@/api/api';

const defaultPagination: IPagination = {
  size: 10,
  total: 1,
};

const typeList = ['callable', 'transfer', 'stash', 'pledge'];
const processData = (type: string, data: any[]) => {
  if (typeList.includes(type)) {
    return data.map((transfer) => {
      return {
        ext: transfer.data,
        events: transfer.events,
        block: transfer.id.replace(/-.*/, ''),
        extIndex: transfer.id,
        timestamp: transfer.timestamp,
      };
    });
  } else {
    return [];
  }
};
const Callable: React.FC = () => {
  const { api } = useApi();
  const intl = useIntl();
  const location = useLocation();
  const [loading, setLoading] = useState<boolean>(false);
  const [tableData, setTableData] = useState<any[]>([]);
  const [pagination, setPagination] = useState<IPagination>(defaultPagination);
  const type = useMemo(() => {
    setTableData([]);
    let type = (location as any).query.type;
    if (!type) {
      type = location.pathname.replace('/', '');
    }
    return type;
  }, [location]);
  const typeColumns: any = useMemo(() => {
    return {
      default: [
        {
          title: `${intl.formatMessage({
            id: 'callable',
          })} ID`,
          dataIndex: 'index',
          width: 150,
        },
        {
          title: intl.formatMessage({
            id: 'block',
          }),
          dataIndex: 'block',
          width: 120,
        },
        {
          title: `${intl.formatMessage({
            id: 'hash',
          })}`,
          dataIndex: 'hash',
          width: 180,
          render: (text: string) => <ShortLink hash={text} path="/account" />,
        },
        {
          title: intl.formatMessage({
            id: 'time',
          }),
          dataIndex: 'timestamp',
          width: 150,
          render: (time: number) => <Time time={time} />,
        },
        {
          title: intl.formatMessage({
            id: 'result',
          }),
          dataIndex: 'result',
        },
        {
          title: intl.formatMessage({
            id: 'action',
          }),
          dataIndex: 'action',
        },
      ],
      transfer: [
        {
          title: `${intl.formatMessage({
            id: 'callable',
          })} ID`,
          dataIndex: 'index',
          width: 150,
        },
        {
          title: intl.formatMessage({
            id: 'block',
          }),
          dataIndex: 'block',
          width: 120,
        },
        {
          title: intl.formatMessage({
            id: 'time',
          }),
          dataIndex: 'timestamp',
          width: 150,
          render: (time: number) => <Time time={time} />,
        },
        {
          title: intl.formatMessage({
            id: 'from',
          }),
          dataIndex: 'from',
          width: 180,
          render: (text: string) => <ShortLink hash={text} path="/account" />,
        },
        {
          title: intl.formatMessage({
            id: 'to',
          }),
          dataIndex: 'to',
          width: 180,
          render: (text: string) => <ShortLink hash={text} path="/account" />,
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
        },
        {
          title: `${intl.formatMessage({
            id: 'hash',
          })}`,
          dataIndex: 'hash',
          render: (text: string) => <ShortLink hash={text} path="/callable" />,
        },
      ],
      stash: [
        {
          title: intl.formatMessage({
            id: 'stashAccount',
          }),
          dataIndex: 'account',
        },
        {
          title: intl.formatMessage({
            id: 'dominator',
          }),
          dataIndex: 'dominator',
        },
        {
          title: intl.formatMessage({
            id: 'amount',
          }),
          dataIndex: 'amount',
        },
        {
          title: intl.formatMessage({
            id: 'block',
          }),
          dataIndex: 'block',
        },
      ],
    };
  }, [intl]);
  const onPageChange = (page: number) => {
    getTableData(page);
  };
  const getTableData = (page: number = 1, size: number = 10) => {
    setLoading(true);
    getRecords(type, {
      page,
      size,
    })
      .then((res) => {
        if (api && res.data) {
          setTableData(processData(type, res.data.list));
          setPagination({ ...pagination, total: res.data.total });
        }
      })
      .finally(() => {
        setLoading(false);
      });
  };
  useEffect(() => {
    getTableData();
  }, [type]);
  useEffect(() => {
    setTableData([]);
  }, [location]);
  let Component: React.FC<any> = BaseTable;
  if (type === 'callable') {
    Component = CallableTable;
  } else if (type === 'transfer') {
    Component = TransferTable;
  }
  return (
    <div className="chain-detail base-container">
      <BaseSearch />
      {api ? (
        <Component
          dataSource={tableData}
          columns={typeColumns[type] ? typeColumns[type] : []}
          onPageChange={onPageChange}
          rowKey="index"
          loading={loading}
          pagination={pagination}
          api={api}
        />
      ) : null}
    </div>
  );
};
export default React.memo(Callable);

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
import { Table, Pagination } from 'antd';
import './baseTable.less';
interface IBaseTable {
  columns: any[];
  dataSource?: any[];
  total?: number;
  loading?: boolean;
  onPageChange?: (page: number, pageSize?: number) => void;
  rowKey?: string;
  pagination?: IPagination | false;
}
const BaseTable: React.FC<IBaseTable> = ({
  columns,
  dataSource = [],
  onPageChange,
  loading,
  rowKey,
  pagination,
}) => {
  return (
    <>
      <Table
        rowKey={rowKey}
        columns={columns}
        dataSource={dataSource}
        pagination={false}
        className="user-table with-padding"
        loading={loading}
      ></Table>
      {pagination ? (
        <Pagination
          className="user-pagination"
          total={pagination.total}
          pageSize={pagination.size}
          showSizeChanger={false}
          onChange={onPageChange}
        />
      ) : null}
      )
    </>
  );
};
export default React.memo(BaseTable);

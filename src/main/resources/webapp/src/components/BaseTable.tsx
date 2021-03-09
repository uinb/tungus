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
import BaseSearch from './BaseSearch';
import './baseTable.less';
interface IBaseTable {
  columns: any[];
  data?: any[];
  type: 'block' | 'extrinsic' | 'account';
  showSearch?: boolean;
  showTable?: boolean;
  onPageChange?: (page: number, pageSize?: number) => void;
}
const Main: React.FC<IBaseTable> = ({
  columns,
  data,
  showSearch = true,
  showTable = true,
  onPageChange,
}) => {
  return (
    <div className="block">
      {showSearch ? <BaseSearch /> : null}
      {showTable ? (
        <>
          <main>
            <Table
              columns={columns}
              dataSource={data}
              pagination={false}
              className="user-table"
            ></Table>
          </main>
          <Pagination
            className="user-pagination"
            total={data?.length}
            showSizeChanger={false}
            onChange={onPageChange}
            hideOnSinglePage={true}
          />
        </>
      ) : null}
    </div>
  );
};
export default Main;

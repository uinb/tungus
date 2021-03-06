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
import { useApi } from '@/context/ApiContext';
import BaseTable from '../../components/BaseTable';
const columns = [
  {
    title: 'Callable ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Block',
    dataIndex: 'blcok',
    key: 'block',
  },
  {
    title: 'Callable Hash',
    dataIndex: 'hash',
    key: 'hash',
  },
  {
    title: 'Time',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: 'Result',
    dataIndex: 'result',
  },
  {
    title: 'Action',
    dataIndex: 'action',
  },
];
const Block: React.FC = (props) => {
  const { api } = useApi();
  return api ? (
    <BaseTable {...props} columns={columns} type="extrinsic" />
  ) : null;
};
export default Block;

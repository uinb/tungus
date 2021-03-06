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
import BaseTable from '@/components/BaseTable';
import './index.less';
const columns = [
  {
    title: 'Account',
    dataIndex: 'uid',
    key: 'uid',
  },
];
const Account: React.FC = () => {
  return (
    <div className="accounts base-container">
      <BaseTable columns={columns} type="account" />
    </div>
  );
};
export default Account;

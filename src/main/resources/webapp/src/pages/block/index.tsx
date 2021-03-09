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
import { useApi } from '@/context/ApiContext';
import { useIntl } from 'umi';
import BaseTable from '../../components/BaseTable';
import './index.less';

const Block: React.FC = (props) => {
  const { api } = useApi();
  const intl = useIntl();
  const columns = useMemo(
    () => [
      {
        title: intl.formatMessage({
          id: 'block',
        }),
        dataIndex: 'block',
      },
      {
        title: intl.formatMessage({
          id: 'status',
        }),
        dataIndex: 'status',
      },
      {
        title: intl.formatMessage({
          id: 'time',
        }),
        dataIndex: 'time',
        key: 'time',
      },
      {
        title: intl.formatMessage({
          id: 'callables',
        }),
        dataIndex: 'callables',
      },
      {
        title: intl.formatMessage({
          id: 'events',
        }),
        dataIndex: 'events',
      },
      {
        title: intl.formatMessage({
          id: 'blockHash',
        }),
        dataIndex: 'blockhash',
      },
    ],
    [intl],
  );
  return api ? <BaseTable {...props} columns={columns} type="block" /> : null;
};
export default Block;

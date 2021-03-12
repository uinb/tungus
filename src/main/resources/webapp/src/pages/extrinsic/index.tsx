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
import BaseTable from '../../components/BaseTable';
import { useLocation, useIntl } from 'umi';

const typeList = ['transfer', 'stash', 'pledge'];
const Block: React.FC = (props) => {
  const intl = useIntl();
  const location = useLocation();
  const type = (location as any).query.type;
  const typeColumns: any = useMemo(() => {
    return {
      default: [
        {
          title: `${intl.formatMessage({
            id: 'callable',
          })} ID`,
          dataIndex: 'id',
        },
        {
          title: intl.formatMessage({
            id: 'block',
          }),
          dataIndex: 'blcok',
        },
        {
          title: `${intl.formatMessage({
            id: 'hash',
          })}`,
          dataIndex: 'hash',
        },
        {
          title: intl.formatMessage({
            id: 'time',
          }),
          dataIndex: 'time',
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
          dataIndex: 'id',
        },
        {
          title: intl.formatMessage({
            id: 'block',
          }),
          dataIndex: 'blcok',
        },
        {
          title: intl.formatMessage({
            id: 'time',
          }),
          dataIndex: 'time',
        },
        {
          title: intl.formatMessage({
            id: 'from',
          }),
          dataIndex: 'from',
        },
        {
          title: intl.formatMessage({
            id: 'to',
          }),
          dataIndex: 'to',
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
  return (
    <BaseTable
      {...props}
      columns={typeColumns[type] ? typeColumns[type] : typeColumns.default}
      type={type}
      showTable={type === undefined || typeList.includes(type)}
    />
  );
};
export default Block;

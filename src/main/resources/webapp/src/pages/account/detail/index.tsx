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

import React, { useState, useEffect } from 'react';
import { NavLink, useParams, useIntl } from 'umi';
import BaseSearch from '@/components/BaseSearch';
import Parameters from '@/components/Parameters';
import { Tabs, Table, Tooltip } from 'antd';
import { RightOutlined, DownOutlined } from '@ant-design/icons';
import CopyText from '@/components/CopyText';
import { formatTime, formatHash } from '@/utils/commonUtils';
import Nodata from '@/components/Nodata';
import { useApi } from '@/context/ApiContext';
import { AccountInfo } from '@polkadot/types/interfaces/system';
const SuccessIcon = require('@/assets/successful.svg');
const FailedIcon = require('@/assets/failed.svg');
const UserIcon = require('@/assets/user.svg');
const { TabPane } = Tabs;
const AccountDetail: React.FC = () => {
  const intl = useIntl();
  const { id } = useParams<IRouteParams>();
  const { api } = useApi();
  const [extrinsicData, setExtrinsicData] = useState<ChainTypes.IExtrinsic[]>(
    [],
  );
  const [userInfo, setUserInfo] = useState<AccountInfo>();
  const getUserInfo = async () => {
    if (api) {
      try {
        const account = await api.query.system.account(id);
        setUserInfo(account);
      } catch (err) {
        console.log(err.message);
      }
    }
  };
  const callableColumns = [
    {
      title: intl.formatMessage({
        id: 'callableId',
      }),
      dataIndex: 'id',
      render: (text: string) => {
        return <NavLink to={'/callable/' + text}>{text}</NavLink>;
      },
    },
    {
      title: intl.formatMessage({
        id: 'hash',
      }),
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
  useEffect(() => {
    getUserInfo();
  }, [id]);
  return (
    <div className="chain-detail base-container">
      <BaseSearch />
      {userInfo ? (
        <>
          <div className="base-card">
            <ul className="block-info">
              <li>
                <h4>
                  {intl.formatMessage({
                    id: 'account',
                  })}
                </h4>
                <CopyText text={id} prefixIcon={UserIcon} />
              </li>
              <li>
                <h4>Free</h4>
                <span>{userInfo.data.free.toHuman()}</span>
              </li>
              <li>
                <h4>Reserved</h4>
                <span>{userInfo.data.reserved.toHuman()}</span>
              </li>
            </ul>
          </div>
          <div className="base-card">
            <Tabs className="user-tab">
              <TabPane
                tab={`${intl.formatMessage({
                  id: 'callables',
                })} (${extrinsicData?.length})`}
                key="callable"
              >
                <Table
                  className="user-table f14"
                  columns={callableColumns}
                  dataSource={extrinsicData}
                  expandable={{
                    expandedRowRender: (row) => <Parameters extrinsic={row} />,
                    expandIcon: ({ expanded, record, onExpand }) => (
                      <span
                        className="expand-button"
                        onClick={(e) => onExpand(record, e)}
                      >
                        {expanded ? <DownOutlined /> : <RightOutlined />}
                      </span>
                    ),
                    expandIconColumnIndex: 5,
                  }}
                  pagination={false}
                />
              </TabPane>
              <TabPane
                tab={`${intl.formatMessage({
                  id: 'transfer',
                })} (0)`}
                key="transfer"
              ></TabPane>
              <TabPane
                tab={`${intl.formatMessage({
                  id: 'stash',
                })} TAO (0)`}
                key="stash"
              ></TabPane>
            </Tabs>
          </div>
        </>
      ) : (
        <Nodata />
      )}
    </div>
  );
};
export default AccountDetail;

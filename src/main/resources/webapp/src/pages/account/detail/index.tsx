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

import React, { useState, useEffect, useCallback } from 'react';
import { useParams, useIntl } from 'umi';
import BaseSearch from '@/components/BaseSearch';
import CallableTable from '@/components/CallableTable';
import TransferTable from '@/components/TransferTable';
import { Tabs, Spin, Pagination } from 'antd';
import CopyText from '@/components/CopyText';
import Nodata from '@/components/Nodata';
import { useApi } from '@/context/ApiContext';
import { AccountInfo } from '@polkadot/types/interfaces/system';
import { getAccountData } from '@/api/api';
interface IPage {
  pageSize: number;
  current: number;
  total: number;
}
const UserIcon = require('@/assets/user.svg');
const { TabPane } = Tabs;

const defaultPageOptions = {
  pageSize: 5,
  current: 1,
  total: 0,
};
const defaultTotalMap = {
  callableTotal: 0,
  transferTotal: 0,
  stashTotal: 0,
};
const AccountDetail: React.FC = () => {
  const intl = useIntl();
  const { id } = useParams<IRouteParams>();
  const { api } = useApi();
  const [callableData, setCallableData] = useState<any[]>([]);
  const [transferData, setTransferData] = useState<any[]>([]);
  const [stashData, setStashData] = useState<any[]>([]);
  const [totalMap, setTotalMap] = useState(defaultTotalMap);
  const [userInfo, setUserInfo] = useState<AccountInfo>();
  const [activeKey, setActiveKey] = useState<string>('callable');
  const [pageOptions, setPageOptions] = useState<IPage>(defaultPageOptions);
  const [loading, setLoading] = useState<boolean>(false);
  const onTabChange = useCallback((key: string) => {
    setActiveKey(key);
    setPageOptions(defaultPageOptions);
    getSingleList(key, defaultPageOptions);
  }, []);
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
  const getDataList = (key: string, options?: IPage) => {
    return new Promise((resolve, reject) => {
      getAccountData(key, {
        account: id,
        page: options ? options.current : pageOptions.current,
        size: options ? options.pageSize : pageOptions.pageSize,
      })
        .then((res) => {
          const data = res.data.list;
          const total = res.data.total;
          if (key === 'callable') {
            setCallableData(data);
            setTotalMap((totalMap) => ({
              ...totalMap,
              callableTotal: total,
            }));
          } else if (key === 'transfer') {
            setTransferData(data);
            setTotalMap((totalMap) => ({
              ...totalMap,
              transferTotal: total,
            }));
          } else {
            setStashData(data);
            setTotalMap((totalMap) => ({
              ...totalMap,
              stashTotal: total,
            }));
          }
          setPageOptions((preOptions) => ({
            ...preOptions,
            total: res.data.total,
          }));
          resolve(data);
        })
        .catch((err) => {
          reject(err);
        });
    });
  };
  const getSingleList = (type?: string, options?: IPage) => {
    let key = type ? type : activeKey;
    setLoading(true);
    getDataList(key, options).finally(() => {
      setLoading(false);
    });
  };
  const getAllList = () => {
    setLoading(true);
    Promise.all([
      getDataList('callable'),
      getDataList('transfer'),
      getDataList('stash'),
    ])
      .then()
      .finally(() => {
        setLoading(false);
      });
  };
  const onPageChange = (current: number) => {
    setPageOptions((preOptions) => {
      return {
        ...preOptions,
        current,
      };
    });
    getSingleList(undefined, {
      ...pageOptions,
      current,
    });
  };

  useEffect(() => {
    getUserInfo();
  }, [id]);
  useEffect(() => {
    getAllList();
  }, [id]);
  return (
    <div className="chain-detail base-container">
      <BaseSearch />
      {userInfo && api ? (
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
          <Spin spinning={loading}>
            <div className="base-card">
              <Tabs
                className="user-tab"
                activeKey={activeKey}
                onChange={onTabChange}
              >
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'callables',
                  })} (${totalMap.callableTotal})`}
                  key="callable"
                >
                  {callableData ? (
                    <CallableTable api={api} dataSource={callableData} />
                  ) : (
                    <Nodata />
                  )}
                </TabPane>
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'transfer',
                  })} (${totalMap.transferTotal})`}
                  key="transfer"
                >
                  {transferData ? (
                    <TransferTable api={api} dataSource={transferData} />
                  ) : (
                    <Nodata />
                  )}
                </TabPane>
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'stash',
                  })} (${totalMap.stashTotal})`}
                  key="stash"
                ></TabPane>
              </Tabs>
            </div>
          </Spin>
          <Pagination
            className="user-pagination"
            {...pageOptions}
            showSizeChanger={false}
            onChange={onPageChange}
            hideOnSinglePage={false}
          />
        </>
      ) : (
        <Nodata />
      )}
    </div>
  );
};
export default React.memo(AccountDetail);

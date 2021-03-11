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

const UserIcon = require('@/assets/user.svg');

const { TabPane } = Tabs;
interface IPage {
  pageSize: number;
  current: number;
  total: number;
}
interface IData {
  ext: string;
  events: string;
  ext1st: string;
  block: number;
  extIndex: string;
}
const defaultPageOptions = {
  pageSize: 5,
  current: 1,
  total: 0,
};
const defaultLoadingMap = {
  callable: false,
  transfer: false,
  stash: false,
};
const AccountDetail: React.FC = () => {
  const intl = useIntl();
  const { id } = useParams<IRouteParams>();
  const { api } = useApi();
  const [callableData, setCallableData] = useState<IData[]>([]);
  const [transferData, setTransferData] = useState<IData[]>([]);
  const [stashData, setStashData] = useState<IData[]>([]);
  const [userInfo, setUserInfo] = useState<AccountInfo>();
  const [activeKey, setActiveKey] = useState<string>('callable');
  const [pageOptions, setPageOptions] = useState<IPage>(defaultPageOptions);
  const [loadingMap, setLoadingMap] = useState<{
    [key: string]: boolean;
  }>(defaultLoadingMap);
  const onTabChange = useCallback((key: string) => {
    setActiveKey(key);
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
  const getDataList = (type?: string, options?: IPage) => {
    let key = type ? type : activeKey;
    setLoadingMap({ ...loadingMap, [key]: true });
    getAccountData(key, {
      account: id,
      page: options ? options.current : pageOptions.current,
      size: options ? options.pageSize : pageOptions.pageSize,
    })
      .then((res) => {
        const data = res.data.list;
        if (key === 'callable') {
          setCallableData(data);
        } else if (key === 'transfer') {
          setTransferData(data);
        } else {
          setStashData(data);
        }
        setPageOptions((preOptions) => ({
          ...preOptions,
          total: res.data.total,
        }));
      })
      .finally(() => {
        setLoadingMap({
          ...loadingMap,
          [key]: false,
        });
      });
  };
  const onPageChange = (current: number) => {
    setPageOptions((preOptions) => {
      return {
        ...preOptions,
        current,
      };
    });
    getDataList(undefined, {
      ...pageOptions,
      current,
    });
  };

  useEffect(() => {
    getUserInfo();
  }, [id]);
  useEffect(() => {
    getDataList('callable');
    getDataList('transfer');
    getDataList('stash');
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
          <Spin spinning={loadingMap[activeKey]}>
            <div className="base-card">
              <Tabs
                className="user-tab"
                activeKey={activeKey}
                onChange={onTabChange}
              >
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'callables',
                  })} (${callableData.length})`}
                  key="callable"
                >
                  <CallableTable api={api} dataList={callableData} />
                </TabPane>
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'transfer',
                  })} (${transferData.length})`}
                  key="transfer"
                >
                  <TransferTable api={api} dataList={transferData} />
                </TabPane>
                <TabPane
                  tab={`${intl.formatMessage({
                    id: 'stash',
                  })} TAO (${stashData.length})`}
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

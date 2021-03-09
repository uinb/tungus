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

import React, { FC, useContext } from 'react';
import { Table } from 'antd';
import BaseSearch from '@/components/BaseSearch';
import BlockList from './BlockList';
import { useIntl, NavLink } from 'umi';
import { useApi } from '@/context/ApiContext';
import FinalizedContext from '@/context/FinalizedContext';
import { formatNumber } from '@/utils/commonUtils';
import './index.less';

interface ITransferProps {
  index: string;
  from: string;
  to: string;
  amount: number;
  timeStamp: number;
}
interface IStashProps {
  account: string;
  dominator: string;
  amount: number;
  block: number;
}
interface IPledgeProps {
  account: string;
  amount: number;
  block: number;
}
const Main: FC = () => {
  const intl = useIntl();
  const finalizedHeader = useContext(FinalizedContext);
  const stashColumns = [
    {
      title: intl.formatMessage({ id: 'stashAccount' }),
      dataIndex: 'account',
    },
    {
      title: intl.formatMessage({ id: 'dominator' }),
      dataIndex: 'dominator',
    },
    {
      title: intl.formatMessage({ id: 'amount' }),
      dataIndex: 'amount',
    },
    {
      title: intl.formatMessage({ id: 'block' }),
      dataIndex: 'block',
    },
  ];
  const pledgeColumns = [
    {
      title: intl.formatMessage({ id: 'pledgeAccount' }),
      dataIndex: 'plege',
      key: 'plege',
    },
    {
      title: intl.formatMessage({ id: 'amount' }),
      dataIndex: 'amount',
      key: 'amount',
    },
    {
      title: intl.formatMessage({ id: 'block' }),
      dataIndex: 'block',
      key: 'block',
    },
  ];
  return (
    <div className="home">
      <section className="search-area">
        <h2>FUSOSCAN</h2>
        <BaseSearch split2={true} />
      </section>
      <section className="total-area">
        <div className="total-card">
          <img src={require('../../assets/final_block.svg')} alt="" />
          <div>
            <h3>{formatNumber(finalizedHeader?.number.toNumber())}</h3>
            <span>{intl.formatMessage({ id: 'finalizedBlock' })}</span>
          </div>
        </div>
        <div className="total-card">
          <img src={require('../../assets/pledge_address.svg')} alt="" />
          <div>
            <h3>0</h3>
            <span>{intl.formatMessage({ id: 'pledgeAddress' })}</span>
          </div>
        </div>
        <div className="total-card">
          <img src={require('../../assets/pledge_quantity.svg')} alt="" />
          <div>
            <h3>0</h3>
            <span>{intl.formatMessage({ id: 'pledgeQuantity' })}</span>
          </div>
        </div>
        <div className="total-card">
          <img src={require('../../assets/stash_quantity.svg')} alt="" />
          <div>
            <h3>0</h3>
            <span>{intl.formatMessage({ id: 'stashQuantity' })}</span>
          </div>
        </div>
      </section>
      <section className="detail-area">
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/stash.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'latestBlocks' })}</span>
            </div>
            <NavLink to="/block">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <BlockList />
        </div>
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/transfer.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'transfers' })}</span>
            </div>
            <NavLink to="/callable">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <ul>
            <li>
              <div className="left">
                <div className="top">
                  <span>Index#</span>
                  <span className="underline">123-1</span>
                </div>
                <div className="bottom">
                  <span className="keyword">From</span>
                  <span className="underline">1qweasdasdaweqw</span>
                  <span className="keyword">To</span>
                  <span className="underline">1assdfasdfsdfsd</span>
                </div>
              </div>
              <div className="right">
                <h5>3 secs ago</h5>
                <h4>123.9999 TAO</h4>
              </div>
            </li>
          </ul>
        </div>
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/logo_square.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'stash' })} TAO</span>
            </div>
            <NavLink to="/callable">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <main
            style={{
              marginTop: '16px',
              padding: '0 24px',
            }}
          >
            <Table
              columns={stashColumns}
              dataSource={[]}
              pagination={false}
              className="user-table"
            ></Table>
          </main>
        </div>
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/logo_square.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'pledge' })} TAO</span>
            </div>
            <NavLink to="/callable">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <main
            style={{
              marginTop: '16px',
              padding: '0 24px',
            }}
          >
            <Table
              columns={pledgeColumns}
              dataSource={[]}
              pagination={false}
              className="user-table"
            ></Table>
          </main>
        </div>
      </section>
    </div>
  );
};
const Home: FC = (props) => {
  const { api } = useApi();
  return api ? <Main {...props} /> : null;
};
export default Home;

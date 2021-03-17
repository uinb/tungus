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
import TransferList from './TransferList';
import { useIntl, NavLink } from 'umi';
import FinalizedContext from '@/context/FinalizedContext';
import { useApi } from '@/context/ApiContext';
import { formatNumber } from '@/utils/commonUtils';
import './index.less';

const Home: FC = () => {
  const { api } = useApi();
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
        <div className="base-container">
          <h2>FUSOSCAN</h2>
          <BaseSearch split2={true} />
        </div>
      </section>
      <section className="total-area base-container">
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
      <section className="detail-area base-container">
        <div className="detail-card block-list">
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
        <div className="detail-card transfer-list">
          <header>
            <div className="title">
              <img src={require('../../assets/transfer.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'transfers' })}</span>
            </div>
            <NavLink to="/callable?type=transfer">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          {api ? <TransferList api={api} /> : null}
        </div>
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/logo_square.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'stash' })} TAO</span>
            </div>
            <NavLink to="/callable?type=stash">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <div style={{ marginTop: '16px', padding: '0 16px' }}>
            <Table
              columns={stashColumns}
              dataSource={[]}
              pagination={false}
              className="user-table"
            />
          </div>
        </div>
        <div className="detail-card">
          <header>
            <div className="title">
              <img src={require('../../assets/logo_square.svg')} alt="" />
              <span>{intl.formatMessage({ id: 'pledge' })} TAO</span>
            </div>
            <NavLink to="/callable?type=pledge">
              {intl.formatMessage({ id: 'viewAll' })}
            </NavLink>
          </header>
          <div
            style={{
              padding: '0 16px',
              marginTop: '16px',
            }}
          >
            <Table
              columns={pledgeColumns}
              dataSource={[]}
              pagination={false}
              className="user-table"
            />
          </div>
        </div>
      </section>
    </div>
  );
};

export default Home;

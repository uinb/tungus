import React from 'react';
import { useIntl } from 'umi';
import BaseSearch from '@/components/BaseSearch';
import { SearchOutlined } from '@ant-design/icons';
import CopyText from '@/components/CopyText';
import { Tabs, Table, Pagination, DatePicker } from 'antd';
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

import { ColumnType } from 'antd/lib/table';
import ShortHashLink from '@/components/ShortLink';
import './index.less';

const { TabPane } = Tabs;
const { RangePicker } = DatePicker;
const transferColumns: ColumnType<any>[] = [
  {
    title: 'Hash',
    dataIndex: 'hash',
    render: (value: string) => <ShortHashLink hash={value} path="/callable" />,
    width: 180,
  },
  {
    title: 'Block',
    dataIndex: 'blockNumber',
    render: (value: string, record) => (
      <ShortHashLink
        text={value}
        hash={record.blockHash}
        path="/block"
        isUnderline={true}
      />
    ),
  },
  {
    title: 'Age',
    dataIndex: 'age',
  },
  {
    title: 'From',
    dataIndex: 'from',
    render: (value: string) => <ShortHashLink path="/account" hash={value} />,
    width: 130,
  },
  {
    title: '',
    dataIndex: 'temp',
    render: () => <img src={require('@/assets/to.svg')} alt="" />,
  },
  {
    title: 'To',
    dataIndex: 'to',
    render: (value: string) => <ShortHashLink path="/account" hash={value} />,
    width: 140,
  },
  {
    title: 'Status',
    dataIndex: 'status',
  },
  {
    title: 'Result',
    dataIndex: 'result',
  },
  {
    title: 'Amount',
    dataIndex: 'amount',
  },
  {
    title: 'Token',
    dataIndex: 'token',
  },
];
const data = [
  {
    hash: '0x773d7fd744ffaa547ce7b2bd8d46586b93f1221a0aafbbc78f0c2b3902881ad9',
    key: 1,
    blockNumber: 9391,
    blockHash:
      '0x7d15db96246f371513790bf0a573b7bcc2b84405a2ad084805c39cf6277a7e13',
    from: '5GrwvaEF5zXb26Fz9rcQpDWS57CtERHpNehXCPcNoHGKutQY',
    to: '5EboZVJLT1sZcCro27LnhJmtwJeWVB6cCVpuNhokuzXQXKCy',
    status: 'UNCONFIRMED',
    result: 'success',
    amount: '0.23423423423',
    token: 'TAO',
  },
];
const tokenColumns: ColumnType<any>[] = [
  {
    title: 'Rank',
    render: (value, record, index) => index + 1,
  },
  {
    title: 'Address',
    dataIndex: 'address',
    render: (value) => (
      <ShortHashLink
        isUnderline={true}
        path="/account"
        hash={value}
        text={value}
      />
    ),
  },
  {
    title: 'Quantity',
    dataIndex: 'quantity',
  },
  {
    title: 'Percentage',
    dataIndex: 'percentage',
  },
];
const data1 = [
  {
    key: '0',
    address: 'R7BUFRQeq1w5jAZf1FKx85SHuX6PfMqsV',
  },
];
const Token: React.FC = () => {
  const intl = useIntl();
  return (
    <div className="token base-container">
      <BaseSearch />
      <h3>
        {intl.formatMessage({
          id: 'tokenDetails',
        })}
      </h3>
      <div className="base-card">
        <div className="token-des">
          <img src={require('@/assets/logo_round.svg')} alt="" />
          <div className="text">
            <h4>TAO</h4>
            <p>
              Tao is the exclusive token of the cryptocurrency on the Fusotao
              chain, which allows users to store Tao in their wallets to obtain
              the amount of the Fusotao chain. Users will be able to earn a
              random number of TAO through an airdrop activity on the trading
              system, and when the top-up trading cryptocurrency feature goes
              live, they will also be able to earn TAO through secondary market
              transactions.
            </p>
          </div>
        </div>
        <div className="detail">
          <div>
            <h4>
              {intl.formatMessage({
                id: 'overview',
              })}
            </h4>
            <ul>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'totalSupply',
                  })}
                </span>
                <span>15,000.000000 TAO</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'circulatingSupply',
                  })}
                </span>
                <span>15,000.000000 TAO</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'holders',
                  })}
                </span>
                <span>23,678 Addresses</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'transfers',
                  })}
                </span>
                <span>123 Txns</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'price',
                  })}
                </span>
                <span>
                  54.78 USD ≈ 123 TAO
                  <span>-2.06%</span>
                  <span>Trade</span>
                </span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'marketCap',
                  })}{' '}
                  /{' '}
                  {intl.formatMessage({
                    id: 'fullyMC',
                  })}
                </span>
                <span>765,78 USD / 765,77 USD</span>
              </li>
            </ul>
          </div>
          <div>
            <h4>
              {intl.formatMessage({
                id: 'more',
              })}
            </h4>
            <ul>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'contract',
                  })}
                </span>
                <CopyText
                  text={'5EboZVJLT1sZcCro27LnhJmtwJeWVB6cCVpuNhokuzXQXKCy'}
                  path="/account"
                />
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'precision',
                  })}
                </span>
                <span>9</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'issuingTime',
                  })}
                </span>
                <span>2020-09-08 09:00:00 (UTC)</span>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'issuer',
                  })}
                </span>
                <CopyText
                  text={'5EboZVJLT1sZcCro27LnhJmtwJeWVB6cCVpuNhokuzXQXKCy'}
                  path="/account"
                />
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'website',
                  })}
                </span>
                <a href="https://fusotao.org" className="yellow">
                  https://fusotao.org
                </a>
              </li>
              <li>
                <span>
                  {intl.formatMessage({
                    id: 'whitepaper',
                  })}
                </span>
                <a href="https://fusotao.org/tao.pdf" className="yellow">
                  https://fusotao.org/tao.pdf
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div className="base-card">
        <Tabs className="user-tab">
          <TabPane
            tab={intl.formatMessage({
              id: 'tokenTransfers',
            })}
            key="transfer"
          >
            <div className="bar">
              <p>
                {intl.formatMessage(
                  {
                    id: 'tokenTransferInfo',
                  },
                  {
                    total: '310,885',
                    current: '10,000',
                  },
                )}
              </p>
              <RangePicker
                placeholder={['Start time (UTC)', 'End time (UTC)']}
                suffixIcon={false}
                separator="~"
                allowClear={false}
              />
            </div>
            <Table
              className="user-table"
              columns={transferColumns}
              dataSource={data}
              pagination={false}
            ></Table>
          </TabPane>
          <TabPane
            tab={intl.formatMessage({
              id: 'tokenHolders',
            })}
            key="holder"
          >
            <div className="bar">
              <p>
                {intl.formatMessage(
                  {
                    id: 'tokenHolderInfo',
                  },
                  {
                    total: '10,000',
                  },
                )}
              </p>
              <div className="user-search">
                <input type="text" placeholder="Search Address" />
                <button>
                  <SearchOutlined />
                </button>
              </div>
            </div>
            <Table
              className="user-table"
              columns={tokenColumns}
              dataSource={data1}
              pagination={false}
            ></Table>
          </TabPane>
        </Tabs>
      </div>
      <Pagination
        style={{
          margin: '32px 0 80px',
        }}
        className="user-pagination"
        total={100}
        showSizeChanger={false}
      />
    </div>
  );
};
export default Token;

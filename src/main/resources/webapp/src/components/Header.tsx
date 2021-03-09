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

import React, { useState, useRef, useEffect, useMemo } from 'react';
import './header.less';
import { Menu } from 'antd';
import { DownOutlined } from '@ant-design/icons';
import { Link, useIntl, useLocation, setLocale, getLocale } from 'umi';

const { SubMenu } = Menu;
interface IMenu {
  key: string;
  title: string;
  submenu?: IMenu[];
}
const locales = ['en_US', 'zh_CN', 'zh_HK'];
const Header: React.FC = (props) => {
  const intl = useIntl();
  const location = useLocation();
  const menuSelectedKeys = useMemo(() => {
    const type = (location as any).query.type;
    if (type === undefined) {
      return [location.pathname];
    } else {
      return ['/' + type];
    }
  }, [location]);
  const [locale, setCurLocale] = useState(getLocale());
  const [dropdownState, setDropdownState] = useState(false);
  const dropClickRef = useRef<HTMLDivElement>(null);
  const dropdownRef = useRef<HTMLUListElement>(null);
  useEffect(() => {
    const handleClick = (e: MouseEvent) => {
      if (
        e.target === dropClickRef.current ||
        e.target === dropdownRef.current ||
        (dropdownRef.current as any).contains(e.target) ||
        (dropClickRef.current as any).contains(e.target)
      ) {
        return;
      }
      setDropdownState(false);
    };
    document.addEventListener('click', handleClick);
    return () => {
      document.removeEventListener('click', handleClick);
    };
  }, [dropdownRef.current]);
  return (
    <header
      style={{
        borderBottom: '2px solid #000000',
      }}
    >
      <div className="dev">
        <a href="https://github.com/uinb/tungus">
          <p>Dev</p>
          <p>Commit: {COMMIT}</p>
        </a>
      </div>
      <div className="header">
        <Link to="/">
          <img className="logo" src={require(`@/assets/logo.svg`)} alt="" />
        </Link>
        <div className="right">
          <Menu
            mode="horizontal"
            selectedKeys={menuSelectedKeys}
            triggerSubMenuAction="click"
          >
            <Menu.Item key="/">
              <Link to="/">
                {intl.formatMessage({
                  id: 'home',
                })}
              </Link>
            </Menu.Item>
            <SubMenu
              popupClassName="user-submenu"
              key="/chain"
              title={
                <span>
                  <span
                    style={{
                      marginRight: '5px',
                    }}
                  >
                    {intl.formatMessage({
                      id: 'chain',
                    })}
                  </span>
                  <DownOutlined />
                </span>
              }
              popupOffset={[-18, 5]}
            >
              <Menu.Item key="/block">
                <Link to="/block">
                  {intl.formatMessage({
                    id: 'blocks',
                  })}
                </Link>
              </Menu.Item>
              <Menu.Item key="/callable">
                <Link to="/callable">
                  {intl.formatMessage({
                    id: 'callables',
                  })}
                </Link>
              </Menu.Item>
              <Menu.Item key="/transfer">
                <Link to="/callable?type=transfer">
                  {intl.formatMessage({
                    id: 'transfers',
                  })}
                </Link>
              </Menu.Item>
              <Menu.Item key="/stash">
                <Link to="/callable?type=stash">
                  {intl.formatMessage({
                    id: 'stash',
                  })}
                </Link>
              </Menu.Item>
              <Menu.Item key="/pledge">
                <Link to="/callable?type=pledge">
                  {intl.formatMessage({
                    id: 'pledge',
                  })}
                </Link>
              </Menu.Item>
            </SubMenu>
            <Menu.Item key="/account">
              <Link to="/account">
                {intl.formatMessage({
                  id: 'accounts',
                })}
              </Link>
            </Menu.Item>
            <Menu.Item key="/token">
              <Link to="/token">
                {intl.formatMessage({
                  id: 'token',
                })}
              </Link>
            </Menu.Item>
          </Menu>
          <div
            className="split-line"
            style={{
              height: '22px',
            }}
          ></div>
          <div className="toggle-locale">
            <div
              className="toggle-click"
              onClick={() => {
                setDropdownState(!dropdownState);
              }}
              ref={dropClickRef}
            >
              <img
                src={require(`@/assets/flags/${locale}.svg`)}
                style={{
                  marginRight: '10px',
                }}
                alt=""
              />
              <DownOutlined />
            </div>
            <ul
              className="dropdown"
              style={{
                display: dropdownState ? 'block' : 'none',
              }}
              ref={dropdownRef}
            >
              {locales.map((item) => {
                return (
                  <li
                    className={locale === item ? 'active' : ''}
                    key={item}
                    onClick={() => {
                      setLocale(item, false);
                      setCurLocale(item);
                      setDropdownState(false);
                    }}
                  >
                    <img src={require(`@/assets/flags/${item}.svg`)} alt="" />
                  </li>
                );
              })}
            </ul>
          </div>
        </div>
      </div>
    </header>
  );
};
export default Header;

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

import React, { useState, useRef } from 'react';
import classnames from 'classnames';
import { SearchOutlined } from '@ant-design/icons';
import { Select, Input, Button } from 'antd';
import { useIntl, useHistory } from 'umi';
import { scan_search } from '@/api/api';

const { Option } = Select;

interface ISearchProps {
  showSelect?: boolean;
  split1?: boolean;
  split2?: boolean;
  showFilter?: boolean;
  style?: React.CSSProperties;
  className?: string;
  placeholder?: string;
}
const BaseSearch: React.FC<ISearchProps> = ({
  showSelect = true,
  split2 = false,
  showFilter = true,
  style,
  className,
  placeholder,
}) => {
  const intl = useIntl();
  const history = useHistory();
  const inputRef = useRef<any>(null);
  const [key, setKey] = useState<string>('');
  const [searchType, setSearchType] = useState('all');

  const handleSearch = () => {
    if (key === '') {
      return;
    }
    if (/^\d+$/.test(key)) {
      history.push('/block/' + key);
    } else if (/^\d+-\d+$/.test(key)) {
      history.push('/callable/' + key);
    } else {
      scan_search(key).then((res) => {
        if (res.status === 204) {
          history.push('/block/' + key);
        } else {
          let { type } = res.data;
          if (type === 'callable') {
            history.push('/callable/' + key);
          } else if (type === 'block') {
            history.push('/block/' + key);
          }
        }
      });
    }
  };
  const inputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setKey(e.target.value);
  };
  const inputKeyUp = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      inputRef.current.blur();
      handleSearch();
    }
  };
  const options = [
    {
      value: 'all',
      label: intl.formatMessage({ id: 'all' }),
    },
    {
      value: 'block',
      label: intl.formatMessage({ id: 'block' }),
    },
    {
      value: 'callable',
      label: intl.formatMessage({ id: 'callable' }),
    },
    {
      value: 'account',
      label: intl.formatMessage({ id: 'account' }),
    },
  ];
  const classname = classnames('search-bar', className, {
    'with-filter': showFilter,
  });
  return (
    <div className={classname} style={style}>
      {showSelect ? (
        <>
          <Select
            value={searchType}
            onChange={(value) => {
              setSearchType(value);
            }}
            dropdownClassName="user-dropdown"
          >
            {options.map((item) => (
              <Option key={item.value} value={item.value}>
                {item.label}
              </Option>
            ))}
          </Select>
          <div
            className="space"
            style={{
              width: '1px',
              backgroundColor: '#000',
            }}
          ></div>
        </>
      ) : null}
      <Input
        onChange={inputChange}
        onKeyUp={inputKeyUp}
        ref={inputRef}
        value={key}
        placeholder={
          placeholder
            ? placeholder
            : intl.formatMessage({ id: 'search.placeholder' })
        }
      />
      {split2 ? (
        <div
          className="space"
          style={{
            width: '25px',
            backgroundColor: 'transparent',
          }}
        ></div>
      ) : null}

      <Button
        onClick={handleSearch}
        style={{
          borderRadius: !split2 ? '0 4px 4px 0' : '4px',
        }}
      >
        <SearchOutlined
          style={{
            color: '#fff',
            fontSize: '18px',
          }}
        />
      </Button>
    </div>
  );
};
export default BaseSearch;

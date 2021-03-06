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
import Detail from './Detail';
import { LeftOutlined, RightOutlined } from '@ant-design/icons';
import { Spin } from 'antd';
import Nodata from '@/components/Nodata';
import BaseSearch from '@/components/BaseSearch';

import useBlock from '@/hooks/useBlock';
import { history, useIntl } from 'umi';

const BlockByHash: React.FC<{ blockHash: string }> = ({ blockHash }) => {
  const intl = useIntl();
  const { block, blockLoading, eventRecord } = useBlock(blockHash);
  const blockNumber = useMemo(() => {
    if (block) {
      return block.block.header.number.toNumber();
    }
  }, [block]);
  const getPreOrNext = (type: clickType) => {
    if (!blockNumber) {
      return;
    }
    if (type === 'next') {
      history.push('/block/' + (blockNumber + 1));
    } else {
      if (blockNumber === 0) {
        return;
      } else {
        history.push('/block/' + (blockNumber - 1));
      }
    }
  };
  return (
    <div className="chain-detail">
      <div
        className="search-bar"
        style={{
          marginBottom: '24px',
        }}
      >
        <div>
          <LeftOutlined
            onClick={() => {
              getPreOrNext('pre');
            }}
          />
          <span style={{ margin: '0 24px' }}>
            {intl.formatMessage({ id: 'block' })}#{blockNumber}
          </span>
          <RightOutlined
            onClick={() => {
              getPreOrNext('next');
            }}
          />
        </div>
        <BaseSearch showSelect={false} showFilter={false} />
      </div>
      <Spin spinning={blockLoading} wrapperClassName="user-modal">
        {block && eventRecord && blockNumber ? (
          <Detail
            block={block}
            blockNumber={blockNumber}
            eventRecord={eventRecord}
          />
        ) : (
          <Nodata />
        )}
      </Spin>
    </div>
  );
};
export default BlockByHash;

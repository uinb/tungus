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

import React, { useState, useMemo } from 'react';
import useBlock from '@/hooks/useBlock';
import ExtrinsicPanelByNumber from './ExtrinsicPanelByNumber';
import { Spin } from 'antd';
import Nodata from '@/components/Nodata';
import BaseSearch from '@/components/BaseSearch';

import { useApi } from '@/context/ApiContext';
import { extractExtrinsic, extractEventsWithFilter } from '@/utils/chainUtils';

const ExtrinsicByNumber: React.FC<{ extrinsicId: string }> = ({
  extrinsicId,
}) => {
  const { api } = useApi();
  const [blockNumber, extrinsicIndex] = useMemo(() => extrinsicId.split('-'), [
    extrinsicId,
  ]);
  const [extrinsicState, setExtrinsicState] = useState<any>();
  const { block, blockLoading, eventRecord } = useBlock(blockNumber);
  const eventData = useMemo(() => {
    if (api && block && eventRecord && blockNumber) {
      return extractEventsWithFilter(eventRecord, +blockNumber, {
        extrinsicIndex: +extrinsicIndex,
        dispatch: setExtrinsicState,
        api,
      });
    }
  }, [block, eventRecord, extrinsicIndex]);
  const extrinsic = useMemo(() => {
    if (block && blockNumber && eventData) {
      const extrinsics = extractExtrinsic(
        block.block.extrinsics,
        +blockNumber,
        eventData,
      );
      const extrinsic = extrinsics[+extrinsicIndex];
      return extrinsic;
    }
  }, [block, eventData, extrinsicIndex]);
  return (
    <div className="chain-detail">
      <div
        className="search-bar"
        style={{
          marginBottom: '24px',
        }}
      >
        <div>
          <span style={{ margin: '0 24px' }}>Callable#{extrinsicId}</span>
        </div>
        <BaseSearch showSelect={false} showFilter={false} />
      </div>
      <Spin spinning={blockLoading} wrapperClassName="user-modal">
        {extrinsic && block ? (
          <ExtrinsicPanelByNumber
            extrinsicId={extrinsicId}
            block={block}
            blockNumber={blockNumber}
            extrinsic={extrinsic}
            extrinsicState={extrinsicState}
            eventData={eventData}
          />
        ) : (
          <Nodata />
        )}
      </Spin>
    </div>
  );
};
export default ExtrinsicByNumber;

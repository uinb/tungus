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

import { useEffect, useState } from 'react';
import { isHex } from '@polkadot/util';
import { useApi } from '@/context/ApiContext';

import { SignedBlock } from '@polkadot/types/interfaces/runtime';
import { extractExtrinsic, extractEventsWithFilter } from '@/utils/chainUtils';

export default (blockId: string | undefined, filter?: any) => {
  const { api } = useApi();
  const [block, setBlock] = useState<SignedBlock>();
  const [blockLoading, setBlockLoading] = useState(false);
  const [extrinsicData, setExtrinsicData] = useState<ChainTypes.IExtrinsic[]>();
  const [eventData, setEventData] = useState<ChainTypes.IEvent[]>();

  const getBlock = async () => {
    if (api && blockId) {
      try {
        setBlockLoading(true);
        if (isHex(blockId)) {
          const signedBlock = await api.rpc.chain.getBlock(blockId);
          setBlock(signedBlock);
        } else if (/^\d+$/.test(blockId)) {
          const blockHash = await api.rpc.chain.getBlockHash(blockId);
          const signedBlock = await api.rpc.chain.getBlock(blockHash);
          setBlock(signedBlock);
        }
      } catch (err) {
      } finally {
        setBlockLoading(false);
      }
    }
  };
  const getEvents = async () => {
    if (api && block) {
      const blockNumber = block.block.header.number.toNumber();
      const events = await api.query.system.events.at(block.block.header.hash);
      const blockEvents = extractEventsWithFilter(events, blockNumber, filter);
      setEventData(blockEvents);
      const extrinsics = extractExtrinsic(
        block.block.extrinsics,
        blockNumber,
        blockEvents,
      );
      setExtrinsicData(extrinsics);
    }
  };
  useEffect(() => {
    getBlock();
  }, [api, blockId]);
  useEffect(() => {
    getEvents();
  }, [api, block]);
  return {
    block,
    blockLoading,
    extrinsicData,
    eventData,
  };
};

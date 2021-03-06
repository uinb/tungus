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

export default (blockId: string | undefined) => {
  const { api } = useApi();
  const [block, setBlock] = useState<ISignedBlock>();
  const [blockLoading, setBlockLoading] = useState(false);
  const [eventRecord, setEventRecord] = useState<IEventRecord>();

  const getBlock = async () => {
    if (api && blockId) {
      try {
        setBlockLoading(true);
        if (isHex(blockId)) {
          const signedBlock = await api.rpc.chain.getBlock(blockId);
          setBlock(signedBlock);
          const events = await api.query.system.events.at(
            signedBlock.block.header.hash,
          );
          setEventRecord(events);
        } else if (/^\d+$/.test(blockId)) {
          const blockHash = await api.rpc.chain.getBlockHash(blockId);
          const signedBlock = await api.rpc.chain.getBlock(blockHash);
          setBlock(signedBlock);
          const events = await api.query.system.events.at(
            signedBlock.block.header.hash,
          );
          setEventRecord(events);
        }
      } catch (err) {
        console.log(err);
        setBlock(undefined);
      } finally {
        setBlockLoading(false);
      }
    }
  };
  useEffect(() => {
    getBlock();
  }, [api, blockId]);
  return {
    block,
    blockLoading,
    eventRecord,
  };
};

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
import React, { useEffect, useState } from 'react';
import ShortLink from '@/components/ShortLink';
import { useApi } from '@/context/ApiContext';
import SuccessfulIcon from '@/assets/successful.svg';
import { formatNumber } from '@/utils/commonUtils';
import type { Header } from '@polkadot/types/interfaces/runtime';

interface IBlockProps {
  blockNumber: number;
  blockHash: string;
  callables: number;
  events: number;
  timeStamp: number;
}
const isBlockInList = (block: Header, blockList: Header[]) => {
  return blockList.some((header) => header.number.eq(block.number));
};
const LatestBLockList: React.FC = () => {
  const { api } = useApi();
  const [blockList, setBlockList] = useState<Header[]>([]);
  useEffect(() => {
    let unsub: any = null;
    const subFun = async () => {
      if (api) {
        unsub = await api.rpc.chain.subscribeFinalizedHeads(async (header) => {
          setBlockList((blockList) => {
            if (isBlockInList(header, blockList)) {
              return blockList;
            } else {
              if (blockList.length < 20) {
                return [header].concat(blockList);
              } else {
                return [header].concat(blockList.slice(0, 19));
              }
            }
          });
        });
      }
    };
    subFun();
    return () => {
      unsub & unsub();
    };
  }, [api]);
  return (
    <ul>
      {blockList.map((block) => {
        return (
          <li key={block.number.toNumber()}>
            <div className="left">
              <div className="top">
                <span>Block#</span>
                <ShortLink
                  isUnderline={true}
                  path="/block"
                  hash={block.hash.toString()}
                  text={formatNumber(block.number.toNumber())}
                />
              </div>
              <div className="bottom">
                <span className="keyword">Includes</span>
                <span>0 Extrinsics</span>
                <span>0 Events</span>
              </div>
            </div>
            <div className="right">
              <img src={SuccessfulIcon} alt="" className="status" />
              {/* <FormatTime time={block}/> */}
            </div>
          </li>
        );
      })}
    </ul>
  );
};
export default React.memo(LatestBLockList);

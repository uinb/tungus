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
import Time from '@/components/FormatTime';
import SuccessfulIcon from '@/assets/successful.svg';
import { formatNumber } from '@/utils/commonUtils';
import { getRecords } from '@/api/api';

interface IBlockList {
  blkId: number;
  createTime: number;
  eventsCnt: number;
  extrinsicsCnt: number;
  number: number;
  hash: string;
}

const LatestBLockList: React.FC = () => {
  const [blockList, setBlockList] = useState<IBlockList[]>([]);
  const getBlockRecords = () => {
    getRecords('block', {
      page: 1,
      size: 5,
    }).then((res) => {
      setBlockList(res.data ? res.data.list : []);
    });
  };
  useEffect(() => {
    getBlockRecords();
    const timer = setInterval(() => {
      getBlockRecords();
    }, 6000);
    return () => {
      clearInterval(timer);
    };
  }, []);

  return (
    <ul>
      {blockList.map((block) => {
        return (
          <li key={block.number}>
            <div className="left">
              <div className="top">
                <span>Block#</span>
                <ShortLink
                  isUnderline={true}
                  path="/block"
                  hash={block.hash.toString()}
                  text={formatNumber(block.number)}
                />
              </div>
              <div className="bottom">
                <span className="keyword">Includes</span>
                <span>{block.extrinsicsCnt} Callables</span>
                <span>{block.eventsCnt} Events</span>
              </div>
            </div>
            <div className="right">
              <img src={SuccessfulIcon} alt="" className="status" />
              <Time time={block.createTime} />
            </div>
          </li>
        );
      })}
    </ul>
  );
};
export default React.memo(LatestBLockList);

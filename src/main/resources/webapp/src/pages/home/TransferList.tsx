import React, { useState, useEffect } from 'react';
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';
import { GenericExtrinsic } from '@polkadot/types/extrinsic/Extrinsic';
import type { ApiPromise } from '@polkadot/api';
import { getRecords } from '@/api/api';
interface IResult {
  data: string;
  id: string;
  timestamp: number;
}
interface ITransfer {
  index: string;
  from: string;
  to: string;
  timestamp: number;
  value: string;
}
const TransferList: React.FC<{
  api: ApiPromise;
}> = ({ api }) => {
  const [transferList, setTransferList] = useState<ITransfer[]>([]);
  useEffect(() => {
    getRecords('transfer', {
      page: 1,
      size: 5,
    }).then((res) => {
      if (res.data) {
        setTransferList(
          res.data.list.map((transfer: IResult) => {
            const extrinsic: any = new GenericExtrinsic(
              api.registry,
              transfer.data,
            ).toHuman();
            return {
              index: transfer.id,
              from: extrinsic.signer.Id,
              to: extrinsic.method.args[0].Id,
              value: extrinsic.method.args[1],
              timestamp: transfer.timestamp,
            };
          }),
        );
      }
    });
  }, []);
  return (
    <ul>
      {transferList.map((transfer) => {
        return (
          <li key={transfer.index}>
            <div className="left">
              <div className="top">
                <span>Index#</span>
                <ShortLink
                  isUnderline={true}
                  text={transfer.index}
                  path="/callable"
                />
              </div>
              <div className="bottom">
                <span>
                  <span className="keyword">From</span>
                  <ShortLink
                    isUnderline={true}
                    hash={transfer.from}
                    path="/callable"
                  />
                </span>
                <span>
                  <span className="keyword">To</span>
                  <ShortLink
                    isUnderline={true}
                    hash={transfer.to}
                    path="/callable"
                  />
                </span>
              </div>
            </div>
            <div className="right">
              <Time time={transfer.timestamp} />
              <h4>{transfer.value}</h4>
            </div>
          </li>
        );
      })}
    </ul>
  );
};
export default React.memo(TransferList);

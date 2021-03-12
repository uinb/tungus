import React from 'react';
import ShortLink from '@/components/ShortLink';
import Time from '@/components/FormatTime';

const TransferList: React.FC = () => {
  return (
    <ul>
      <li>
        <div className="left">
          <div className="top">
            <span>Index#</span>
            {/* <span className="underline">123-1</span> */}
            <ShortLink isUnderline={true} text="123-1" path="/callable" />
          </div>
          <div className="bottom">
            <span>
              <span className="keyword">From</span>
              <ShortLink
                isUnderline={true}
                hash="5GrwvaEF5zXb26Fz9rcQpDWS57CtERHpNehXCPcNoHGKutQY"
                path="/callable"
              />
            </span>
            <span>
              <span className="keyword">To</span>
              <ShortLink
                isUnderline={true}
                hash="5EboZVJLT1sZcCro27LnhJmtwJeWVB6cCVpuNhokuzXQXKCy"
                path="/callable"
              />
            </span>
          </div>
        </div>
        <div className="right">
          <Time time={Date.now()} />
          <h4>123.9999 TAO</h4>
        </div>
      </li>
    </ul>
  );
};
export default React.memo(TransferList);

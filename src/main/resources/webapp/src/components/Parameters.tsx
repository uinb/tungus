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

import React from 'react';
import CopyText from '@/components/CopyText';
import { handleStruct } from '@/utils/chainUtils';

interface IProps {
  event?: ChainTypes.IEvent;
  extrinsic?: ChainTypes.IExtrinsic;
  className?: string;
}
const Parameters: React.FC<IProps> = React.memo((props) => {
  const { extrinsic, event, className } = props;
  if (extrinsic) {
    return (
      <ul className={`expanded-row ${className ? className : ''}`}>
        {extrinsic.metaArgs.map((meta, index) => {
          const metaName = meta.name.toHuman();
          let methodInfo = extrinsic.methodArgs[index];
          let methodValue: any;
          if (metaName === 'now') {
            methodValue = extrinsic.methodArgs[index]
              .toString()
              .replaceAll(',', '');
          } else if (metaName === 'dest') {
            methodValue = <CopyText text={(methodInfo.toHuman() as any).Id} />;
          } else {
            methodValue = methodInfo.toHuman();
          }
          return (
            <li key={index}>
              {metaName === 'calls' ? (
                handleStruct(extrinsic.methodArgs)
              ) : (
                <>
                  <h4>{metaName}</h4>
                  <span>{methodValue}</span>
                </>
              )}
            </li>
          );
        })}
      </ul>
    );
  } else if (event) {
    return (
      <ul className="expanded-row">
        <li>
          <h4>Docs</h4>
          <span>{event.meta.documentation.toHuman()}</span>
        </li>
        {event.meta.args.map((arg, index) => {
          return (
            <li key={index}>
              <h4>{arg.toHuman()}</h4>
              <span>
                {JSON.stringify((event.data.toHuman() as any)[index]).replace(
                  /^"|"$/g,
                  '',
                )}
              </span>
            </li>
          );
        })}
      </ul>
    );
  } else {
    return <></>;
  }
});
export default Parameters;

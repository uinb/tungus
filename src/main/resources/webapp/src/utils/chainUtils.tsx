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

import { AnyTuple } from '@polkadot/types/types';
import { GenericExtrinsic } from '@polkadot/types/extrinsic';
import { EventRecord } from '@polkadot/types/interfaces/system';
import { Vec } from '@polkadot/types';
import { ApiPromise } from '@polkadot/api';
export const handleStruct = (methodArgs: AnyTuple) => {
  return (
    <div className="struct">
      {methodArgs.map((arg, index) => {
        return (
          <div className="struct-item" key={index}>
            <div className="struct-title with-border first">calls</div>
            <div className="struct-content">
              {(arg as any).map((item: any, index: number) => {
                const meta = item.meta.toHuman();
                const call = item.toHuman();
                return (
                  <div className="struct-item" key={index}>
                    <div className="struct-title with-border">{index}</div>
                    <div className="struct-content">
                      <div className="struct" key={index}>
                        <div className="struct-item">
                          <div className="struct-title with-border">
                            call_args
                          </div>
                          <div className="struct-content">
                            {meta.args.map((argItem: any, index: number) => {
                              return (
                                <div className="struct" key={index}>
                                  <div className="struct-item">
                                    <div className="struct-title with-border">
                                      {argItem.name}
                                    </div>
                                    <div
                                      className={`struct-content ${
                                        Array.isArray(call.args[index])
                                          ? ''
                                          : 'with-border'
                                      }`}
                                    >
                                      {Array.isArray(call.args[index]) ? (
                                        <div className="strcut">
                                          {call.args[index].map(
                                            (item: any, index: number) => {
                                              return (
                                                <div
                                                  className="struct-item"
                                                  key={index}
                                                >
                                                  <div className="struct-title with-border">
                                                    {index}
                                                  </div>
                                                  <div className="struct-content with-border">
                                                    {item}
                                                  </div>
                                                </div>
                                              );
                                            },
                                          )}
                                        </div>
                                      ) : typeof call.args[index] ===
                                        'object' ? (
                                        JSON.stringify(call.args[index])
                                      ) : (
                                        call.args[index]
                                      )}
                                    </div>
                                  </div>
                                </div>
                              );
                            })}
                          </div>
                        </div>
                        <div className="struct-item">
                          <div className="struct-title with-border">
                            call_function
                          </div>
                          <div className="struct-content with-border">
                            {call.method}
                          </div>
                        </div>
                        <div className="struct-item">
                          <div className="struct-title with-border">
                            call_module
                          </div>
                          <div className="struct-content with-border">
                            {call.section}
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
          </div>
        );
      })}
    </div>
  );
};
export const extractExtrinsic = (
  data: GenericExtrinsic[],
  blockNumber: number,
  blockEvents: ChainTypes.IEvent[],
): ChainTypes.IExtrinsic[] => {
  return data.map((item, index) => {
    let result = 'ExtrinsicSuccess';
    for (let i = 0; i < blockEvents.length; i++) {
      let event = blockEvents[i];
      if (+event.applyExtrinsic === index) {
        result = event.meta.name.toString();
      }
    }
    return {
      id: blockNumber + '-' + index,
      key: blockNumber + '-' + index,
      hash: item.hash.toString(),
      result,
      action: `${item.method.section}(${item.method.method})`,
      section: item.method.section,
      method: item.method.method,
      metaArgs: item.meta.args,
      methodArgs: item.method.args,
      era: item.era,
      isSigned: item.isSigned,
      signer: item.signer.tos,
      signature: item.signature.toString(),
      nonce: item.nonce.toString(),
    };
  });
};
export const extractEventsWithFilter = (
  data: Vec<EventRecord>,
  blockNumber: number,
  filter?: {
    extrinsicIndex: number;
    dispatch: React.Dispatch<React.SetStateAction<any>>;
    api: ApiPromise;
  },
): ChainTypes.IEvent[] => {
  if (filter) {
    const events: ChainTypes.IEvent[] = [];
    let result;
    let reason;
    let fee;
    let {
      runtimeMetadata: {
        asV12: { modules },
      },
    } = filter.api;
    data.forEach((record, index) => {
      let applyExtrinsic = record.phase.isApplyExtrinsic
        ? record.phase.asApplyExtrinsic.toNumber()
        : '';
      if (applyExtrinsic == filter.extrinsicIndex) {
        result = record.event.meta.name.toHuman();
        // 查找失败原因
        if (result === 'ExtrinsicFailed') {
          const {
            Module: { index, error },
          } = record.event.data[0].toHuman() as any;
          modules.forEach((module) => {
            if (module.index.toHuman() === index) {
              reason = module.errors[error].toHuman();
            }
          });
        }
        // 查找费率
        if (result === 'Deposit') {
          fee = record.event.data[1].toHuman();
        }
        events.push({
          id: blockNumber + '-' + index,
          key: blockNumber + '-' + index,
          hash: record.hash.toString(),
          isApplyExtrinsic: record.phase.isApplyExtrinsic,
          applyExtrinsic,
          action: `${record.event.section}(${record.event.method})`,
          meta: record.event.meta,
          data: record.event.data,
        });
      }
    });
    filter.dispatch({
      result,
      reason,
      fee,
    });
    return events;
  }
  return data.map((record, index) => {
    return {
      id: blockNumber + '-' + index,
      key: blockNumber + '-' + index,
      hash: record.hash.toString(),
      isApplyExtrinsic: record.phase.isApplyExtrinsic,
      applyExtrinsic: record.phase.isApplyExtrinsic
        ? record.phase.asApplyExtrinsic.toNumber()
        : '',
      action: `${record.event.section}(${record.event.method})`,
      meta: record.event.meta,
      data: record.event.data,
    };
  });
};

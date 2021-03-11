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
import { GenericEventData } from '@polkadot/types/generic';
import { GenericExtrinsicEra } from '@polkadot/types/extrinsic';
import {
  FunctionArgumentMetadataV9,
  EventMetadataLatest,
} from '@polkadot/types/interfaces/metadata';
import { SignedBlock } from '@polkadot/types/interfaces/runtime';
import { Vec } from '@polkadot/types';
import { EventRecord } from '@polkadot/types/interfaces/system';
import { ApiPromise } from '@polkadot/api';

declare global {
  type clickType = 'pre' | 'next';
  interface IRouteParams {
    id: string;
  }
  interface IEventFilter {
    extrinsicIndex: number;
    dispatch: React.Dispatch<React.SetStateAction<any>>;
    api: ApiPromise;
  }
  type ISignedBlock = SignedBlock;
  type IEventRecord = Vec<EventRecord>;
  namespace ChainTypes {
    interface IEvent {
      id: string;
      key: string;
      hash: string;
      isApplyExtrinsic: boolean;
      applyExtrinsic: number | string;
      action: string;
      meta: EventMetadataLatest;
      data: GenericEventData;
    }
    interface IExtrinsic {
      id: string;
      key: string;
      hash: string;
      result: string;
      action: string;
      section: string;
      method: string;
      methodArgs: AnyTuple;
      metaArgs: Vec<FunctionArgumentMetadataV9>;
      isSigned?: boolean;
      signer?: string;
      nonce?: string;
      signature?: string;
      era?: GenericExtrinsicEra;
      time?: string;
    }
  }
}

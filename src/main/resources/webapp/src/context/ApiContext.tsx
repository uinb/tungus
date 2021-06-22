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

import React, { useEffect, useContext, useState, FC } from 'react';
import { ApiPromise, WsProvider } from '@polkadot/api';
import chainTypes from '../../config/chainTypes';
interface IApiContext {
  api: ApiPromise | null;
  apiState: string;
}
const initState: IApiContext = {
  api: null,
  apiState: '',
};
const ApiContext = React.createContext<IApiContext>(initState);
async function initApi(
  state: IApiContext,
  dispatch: React.Dispatch<React.SetStateAction<IApiContext>>,
) {
  dispatch({
    api: null,
    apiState: 'CONNECTING',
  });
  const provider = new WsProvider(WSS_URL);
  const api = new ApiPromise({
    provider,
    types: chainTypes,
  });
  api.isReady.then(async () => {
    dispatch({
      ...state,
      api,
    });
  });
}
const ApiContextProvider: FC = (props) => {
  const [state, setState] = useState<IApiContext>(initState);
  useEffect(() => {
    initApi(state, setState);
  }, []);
  return (
    <ApiContext.Provider value={state}>{props.children}</ApiContext.Provider>
  );
};
const useApi = () => ({ ...useContext(ApiContext) });
export { ApiContextProvider, useApi };

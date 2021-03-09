import React, { useState, useEffect } from 'react';
import type { Header } from '@polkadot/types/interfaces/runtime';
import { useApi } from './ApiContext';

const FinalizedContext = React.createContext<Header | null>(null);
export const FinalizedProvider: React.FC = (props) => {
  const [finalizedHeader, setFinalizedHeader] = useState<Header | null>(null);
  const { api } = useApi();
  useEffect(() => {
    let unsub: any = null;
    const subFun = async () => {
      if (api) {
        unsub = await api.rpc.chain.subscribeFinalizedHeads((header) => {
          setFinalizedHeader(header);
        });
      }
    };
    subFun();
    return () => {
      unsub && unsub();
    };
  }, [api]);
  return (
    <FinalizedContext.Provider value={finalizedHeader}>
      {props.children}
    </FinalizedContext.Provider>
  );
};
export default FinalizedContext;

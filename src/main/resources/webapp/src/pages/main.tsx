import React from 'react';
import { Spin } from 'antd';
import Header from '../components/Header';
import Footer from '../components/Footer';
import { useApi, ApiContextProvider } from '@/context/ApiContext';
import { FinalizedProvider } from '@/context/FinalizedContext';
import '../styles/reset.less';
import '../styles/common.less';

const Main: React.FC = (props) => {
  const { apiState } = useApi();
  const Loader = (
    <div className="full-modal">
      <Spin spinning={true} />
    </div>
  );
  if (apiState === 'CONNECTING') {
    return Loader;
  }
  return (
    <>
      <Header />
      <main>{props.children}</main>
      <Footer />
    </>
  );
};
const MainWithProvider: React.FC = (props) => (
  <ApiContextProvider>
    <FinalizedProvider>
      <Main {...props} />
    </FinalizedProvider>
  </ApiContextProvider>
);
export default MainWithProvider;

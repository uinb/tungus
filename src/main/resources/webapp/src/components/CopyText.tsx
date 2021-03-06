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
import { useIntl, NavLink } from 'umi';
import { CopyToClipboard } from 'react-copy-to-clipboard';
import { message } from 'antd';
const CopyIcon = require('@/assets/copy.svg');
interface ICopyProps {
  text: string;
  prefixIcon?: string;
  suffixIcon?: string;
  path?: string;
}
const Main: React.FC<ICopyProps> = ({ text, prefixIcon, suffixIcon, path }) => {
  const intl = useIntl();
  const showMessage = () => {
    message.success(
      intl.formatMessage({
        id: 'COPY_SUCCESS',
      }),
      1,
    );
  };
  return (
    <span className="copy-text">
      {prefixIcon ? (
        <img src={prefixIcon} className="copy-prefix" alt="" />
      ) : null}
      {path ? (
        <NavLink className="yellow" to={path + '/' + text}>
          {text}
        </NavLink>
      ) : (
        <span>{text}</span>
      )}

      <CopyToClipboard text={text} onCopy={showMessage}>
        <img
          className="copy-suffix"
          src={suffixIcon ? suffixIcon : CopyIcon}
          alt=""
        />
      </CopyToClipboard>
    </span>
  );
};
export default Main;

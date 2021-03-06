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
import { Tooltip } from 'antd';
import classnames from 'classnames';
import { formatHash } from '@/utils/commonUtils';
import { NavLink } from 'umi';

interface IShortLink {
  hash?: string;
  path: string;
  text?: string;
  isUnderline?: boolean;
  style?: React.CSSProperties;
}
const ShortHashLink: React.FC<IShortLink> = ({
  hash,
  path,
  isUnderline,
  text,
  style,
}) => {
  const classes = classnames('yellow', {
    underline: isUnderline,
  });
  const fhash = formatHash(hash);
  const Component = (
    <NavLink
      style={style}
      to={path.replace(/\/$/, '') + '/' + (hash || text)}
      className={classes}
    >
      {text ? text : fhash}
    </NavLink>
  );
  return fhash ? (
    <Tooltip title={hash} color="#474747">
      {Component}
    </Tooltip>
  ) : (
    Component
  );
};
export default ShortHashLink;

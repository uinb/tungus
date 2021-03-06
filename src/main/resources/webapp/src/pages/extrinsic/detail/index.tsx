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
import { useParams } from 'umi';

import ExtrinsicByNumber from './ExtrinsicByNumber';
import ExtrinsicByHash from './ExtrinsicByHash';

import './index.less';

const matchReg = /(\d+)-(\d+)/;
const ExtrinsicDetail: React.FC = () => {
  const { id } = useParams<IRouteParams>();
  if (matchReg.test(id)) {
    return <ExtrinsicByNumber extrinsicId={id} />;
  } else {
    return <ExtrinsicByHash extrinisicHash={id} />;
  }
};
export default ExtrinsicDetail;

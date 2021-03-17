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

import axios from 'axios';
interface IPage {
  page: number;
  size: number;
}
axios.defaults.timeout = 5000;
export const scan_search = (key: string) =>
  axios.get('/api/v1/fusotao/search/' + key);

export const getAccountData = <T extends IPage>(type: string, data: T) =>
  axios.post('/api/v1/fusotao/scan/' + type, data);

export const getRecords = <T extends IPage>(type: string, data: T) =>
  axios.post('/api/v1/fusotao/record/' + type, data);

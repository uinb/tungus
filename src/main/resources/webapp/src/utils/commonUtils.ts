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

export const formatTime = (spaceTime: number) => {
  if (spaceTime < 0) {
    return '';
  }
  let d = Math.floor(spaceTime / 1000 / 60 / 60 / 24);
  let h = Math.floor((spaceTime / 1000 / 60 / 60) % 24);
  let m = Math.floor((spaceTime / 1000 / 60) % 60);
  let s = Math.floor((spaceTime / 1000) % 60);
  let tempArr = [];
  if (d > 0) {
    tempArr.push(d + ` day${d > 1 ? 's' : ''} `);
  }
  if (h > 0) {
    tempArr.push(h + ` hr${h > 1 ? 's' : ''} `);
  }
  if (m > 0) {
    tempArr.push(m + ` min${m > 1 ? 's' : ''} `);
  }
  if (s > 0) {
    tempArr.push(s + ` sec${s > 1 ? 's' : ''} `);
  }
  return tempArr.slice(0, 2).join(' ') + 'ago';
};
export const formatHash = (hash?: string) => {
  return hash ? hash.replace(/(.{7})(.*)(.{5})/g, '$1....$3') : '';
};
export const formatNumber = (num?: number) => {
  if (num === undefined) {
    return '';
  }
  let formatVal = num + '';
  if (formatVal.indexOf('.') !== -1) {
    return (num + '').replace(/(?!^)(?=(\d{3})+(\.\d+)$)/g, ',');
  } else {
    return (num + '').replace(/(?!^)(?=(\d{3})+$)/g, ',');
  }
};

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

import React, { useEffect, useState } from 'react';
import dayjs from 'dayjs';
import utc from 'dayjs/plugin/utc';
import { formatTime } from '@/utils/commonUtils';
dayjs.extend(utc);

const Time: React.FC<{
  time: number;
}> = ({ time }) => {
  const [timeStr, setTimeStr] = useState<string>(() => {
    return formatTime(Date.now() - time);
  });
  useEffect(() => {
    const timer = setInterval(() => {
      const spaceTime = Date.now() - time;
      setTimeStr(formatTime(spaceTime));
    }, 1000);
    return () => {
      clearInterval(timer);
    };
  }, [time]);
  return <span>{timeStr}</span>;
};
export default React.memo(Time);

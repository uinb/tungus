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

import { defineConfig, dynamic } from 'umi';

export default defineConfig({
  title: 'FusoScan',
  define: {
    WSS_URL: 'wss://test-fuso.ngnexusccs.xyz',
  },
  proxy: {
    '/api': {
      target: 'http://192.168.11.14:8080',
      changeOrigin: true,
    },
  },
  nodeModulesTransform: {
    type: 'none',
  },
  history: {
    type: 'hash',
  },
  dynamicImport: {},
  hash: true,
  routes: [
    {
      path: '/',
      component: '@/pages/main',
      exact: false,
      routes: [
        { path: '/', component: '@/pages/home' },
        {
          path: '/block',
          component: '@/pages/block',
          exact: true,
        },
        {
          path: '/block/:id',
          component: '@/pages/block/detail',
          exact: true,
        },
        {
          path: '/callable',
          component: '@/pages/extrinsic',
          exact: true,
        },
        {
          path: '/callable/:id',
          component: '@/pages/extrinsic/detail',
          exact: true,
        },
        {
          path: '/account',
          component: '@/pages/account',
          exact: true,
        },
        {
          path: '/account/:id',
          component: '@/pages/account/detail',
          exact: true,
        },
        {
          path: '/token',
          component: '@/pages/token',
          exact: true,
        },
        {
          component: '@/pages/404',
        },
      ],
    },
  ],
  fastRefresh: {},
  chainWebpack: (config) => {
    config.module
      .rule('mjs')
      .test(/\.mjs$/)
      .type('javascript/auto');
    config
      .plugin('antd-dayjs-webpack-plugin')
      .use('antd-dayjs-webpack-plugin')
      .end();
  },
  locale: {
    default: 'en_US',
    antd: true,
    title: false,
    baseNavigator: false,
    baseSeparator: '_',
  },
});
import axios from "axios";
import router from "../router";
import store from "../store";
import { Notification, MessageBox } from "element-ui";
import { getSign } from "../assets/common/js/common.js";
// axios.defaults.timeout = process.env.NODE_ENV === 'dev'?10000: 5000
axios.defaults.baseURL = process.env.VUE_APP_BASE_URL;
const errorResult = {
  data: {
    result: [],
  },
};
let local = localStorage.getItem("nexus-locale") || "en_US";
let localText = {
  zh_CN: {
    text1: "未授权",
    text2: "请求的资源不存在",
    text3: "服务器错误",
    text4: "请求超时",
    text5: "登录无效或登陆已过期！",
    text6: "网络错误",
  },
  zh_HK: {
    text1: "未授權",
    text2: "請求的資源不存在",
    text3: "服務器錯誤",
    text4: "請求超時",
    text5: "登錄無效或登陸已過期！",
    text6: "網絡錯誤",
  },
  en_US: {
    text1: "unauthorized",
    text2: "The requested resource does not exist",
    text3: "Server Error",
    text4: "Request timed out",
    text5: "The login is invalid or the login has expired!",
    text6: "Network Error",
  },
};
const handleError = ({ status, statusText }) => {
  switch (status) {
    case 403:
      Notification.error(localText[local].text1);
      break;
    case 404:
      Notification.error(localText[local].text2);
      break;
    case 500:
      Notification.error(localText[local].text3);
      break;
    case 504:
      Notification.error(localText[local].text4);
      break;
    default:
      Notification.error(statusText);
  }
};
axios.interceptors.response.use(
  (response) => {
    if (response.data.statusCode == 200) {
      return Promise.resolve(response);
    } else if (
      response.data.statusCode === 101 ||
      response.data.statusCode === 2
    ) {
      store.commit("setToken", "");
      if (
        router.history.current.path !== "/trade" ||
        router.history.current.path !== "/maintenance"
      ) {
        Notification({
          type: "error",
          message: localText[local].text5,
          duration: 2500,
        });
        router.push("/login");
      }
    } else {
      Notification.error(response.data.errorMessage);
      return Promise.resolve(errorResult);
    }
  },
  (err) => {
    if (err.message === "Network Error") {
      let res = {
        status: 0,
        statusText: localText[local].text6,
      };
      // handleError(res);
      return Promise.reject(res);
    } else {
      let { status, statusText } = err.response;
      handleError({ status, statusText });
      return Promise.reject({
        status,
        statusText,
      });
    }
  }
);
axios.interceptors.request.use((config) => {
  config.headers["X-timestamp"] = new Date().getTime();
  let token = localStorage.getItem("nexus-token");
  let locale = localStorage.getItem("nexus-locale") || "en_US";
  if (token) {
    config.headers["X-token"] = token;
    config.headers["X-access-channel"] = "web";
  }
  config.headers["X-Locales"] = locale;
  return config;
});
//获取短信验证码
export const getPhoneCode = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/send-phone-open";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//获取邮箱验证码
export const getEmailCode = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/send-email-open";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//获取业务短信验证码
export const getSendPhoneCode = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/send-phone";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//获取业务邮箱验证码
export const getSendEmailCode = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/send-email";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//验证码的验证
export const checkVerifyCode = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/verify";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//老用户强制重置登录密码
export const loginPwdReset = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/set-pwd-first";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//重置登录密码
export const resetLoginPwd = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/reset-password";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//注册
export const userRegister = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/regist";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//登录
export const userLogin = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/login";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//登录验证
export const userLoginVerify = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/verify-login";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//登出
export const userLoginOut = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/logout";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
//获得用户信息
export const getUserInfo = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/user-info";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
// 获取实名信息
export const getUserVerifyInfo = () =>
  axios.post("/user/v1/web/user-verify-info");
//检测token是否失效
export const checkToken = (data) => {
  var data = !!data ? data : {};
  let newDatas = { ...data },
    url = "/user/v1/web/token-verify";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};

// 绑定ga信息 /user/v1/web/get-ga-bind-info
export const getGaBindInfo = () => axios.post("/user/v1/web/get-ga-bind-info");
// 获取接口通用验证方式
export const getVerifyWay = () => axios.post("/user/v1/web/verify-way");
// 上传图片
export const fileUpload = (data) => axios.post("/user/v1/file/upload", data);

// 实名认证
export const realnameAuth = (data) => axios.post("/user/v1/web/id-auth", data);

// 获取用户api信息
export const getApiInfo = () => axios.post("/user/v1/web/api-info");
// 操作api
export const operateApi = (data) => {
  let newDatas = { ...data },
    url = "/user/v1/web/api-operation";
  newDatas.sign = getSign(url.split("/").pop(), data);
  return axios.post(url, newDatas);
};
// 用户推荐信息
export const userRecommond = () => axios.post("/user/v1/web/user-recommond");
// 获取所有交易对的统计汇总信息
export const getAllSymbolInfo = () =>
  axios.get("/market/v1/get-all-symbol-info");
//获取所有的币种基础信息
export const findCurrencyList = () =>
  axios.post("/market/v1/find-currency-list");
//获取交易币对列表
export const findSymbolList = () => axios.post("/market/v1/find-symbol-list");
// 获取当前交易对的实时信息
export const getSymbolCurrentPrice = (base = 1001, quote = 1000) =>
  axios.get(`/market/v1/symbol-current-price/${base}/${quote}`);
//获取k线图历史数据
export const getKlineDatas = (data) =>
  axios.post("/market/v1/market-data", data);
// 提交风险评测
export const uploadRiskExam = (data) =>
  axios.post("/user/v1/web/risk-exam", data);

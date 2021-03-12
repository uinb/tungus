import React, { useState, useRef, useEffect } from 'react';
import { DownOutlined } from '@ant-design/icons';
import { getLocale, setLocale } from 'umi';
import './locale.less';
const locales = ['en_US', 'zh_CN', 'zh_HK'];
const Locale: React.FC = () => {
  const [locale, setCurLocale] = useState(getLocale());
  const [dropdownState, setDropdownState] = useState(false);
  const dropClickRef = useRef<HTMLDivElement>(null);
  const dropdownRef = useRef<HTMLUListElement>(null);
  useEffect(() => {
    const handleClick = (e: MouseEvent) => {
      if (
        e.target === dropClickRef.current ||
        e.target === dropdownRef.current ||
        (dropdownRef.current as any).contains(e.target) ||
        (dropClickRef.current as any).contains(e.target)
      ) {
        return;
      }
      setDropdownState(false);
    };
    document.addEventListener('click', handleClick);
    return () => {
      document.removeEventListener('click', handleClick);
    };
  }, [dropdownRef.current]);
  return (
    <div className="toggle-locale">
      <div
        className="toggle-click"
        onClick={() => {
          setDropdownState(!dropdownState);
        }}
        ref={dropClickRef}
      >
        <img
          src={require(`@/assets/flags/${locale}.svg`)}
          style={{
            marginRight: '10px',
          }}
          alt=""
        />
        <DownOutlined />
      </div>
      <ul
        className="dropdown"
        style={{
          display: dropdownState ? 'block' : 'none',
        }}
        ref={dropdownRef}
      >
        {locales.map((item) => {
          return (
            <li
              className={locale === item ? 'active' : ''}
              key={item}
              onClick={() => {
                setLocale(item, false);
                setCurLocale(item);
                setDropdownState(false);
              }}
            >
              <img src={require(`@/assets/flags/${item}.svg`)} alt="" />
            </li>
          );
        })}
      </ul>
    </div>
  );
};
export default Locale;

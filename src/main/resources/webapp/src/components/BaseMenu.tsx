import React, { useState, useContext, useMemo, useEffect } from 'react';
import { useLocation, useHistory } from 'umi';
import { RightOutlined } from '@ant-design/icons';
const MenuContext = React.createContext<{
  curSelected: string;
  setCurSelected: (selected: string) => void;
}>({
  curSelected: '',
  setCurSelected: () => {},
});
interface ISubMenu {
  header: React.ReactNode;
  menuList: IMenu[];
}
interface IProps {
  menuList: IMenu[];
  className?: string;
  style?: React.CSSProperties;
}

export interface IMenu {
  title: string;
  key: string;
  path?: string;
  subMenu?: IMenu[];
  header?: React.ReactNode;
  level?: number;
}
function handleData(menuList: IMenu[]) {
  let level = 0;
  function setLevel(menuList: IMenu[]) {
    let result: IMenu[] = menuList.map((menu) => {
      if (menu.subMenu) {
        level++;
        return { ...menu, subMenu: setLevel(menu.subMenu), level };
      } else {
        return { ...menu, level };
      }
    });
    level--;
    return result;
  }
  return setLevel(menuList);
}

const SubMenu: React.FC<ISubMenu> = ({ header, menuList }) => {
  const [expanded, setExpanded] = useState<boolean>(false);
  return (
    <li className="base-menu-item base-submenu">
      <header
        onClick={() => {
          setExpanded(!expanded);
        }}
      >
        {header}
        <RightOutlined
          style={{
            transform: `rotate(${expanded ? 90 : 0}deg)`,
          }}
        />
      </header>
      <Menu
        menuList={menuList}
        style={{
          height: expanded ? menuList.length * 48 + 'px' : 0,
        }}
      />
    </li>
  );
};
const Menu: React.FC<IProps> = ({ menuList, style }) => {
  const history = useHistory();
  const { curSelected, setCurSelected } = useContext(MenuContext);
  return (
    <ul className="base-menu" style={style}>
      {menuList.map((item) => {
        if (item.subMenu && item.subMenu.length > 0) {
          return (
            <SubMenu
              header={item.header || item.title}
              menuList={item.subMenu}
              key={item.key}
            />
          );
        } else {
          return (
            <li
              className={`base-menu-item ${
                curSelected === '/' + item.key ? 'selected' : ''
              }`}
              key={item.key}
              onClick={() => {
                let path = item.path ? item.path : '/' + item.key;
                setCurSelected('/' + item.key);
                history.push(path);
              }}
            >
              <header>{item.header || item.title}</header>
            </li>
          );
        }
      })}
    </ul>
  );
};
const BaseMenu: React.FC<IProps> = ({ menuList, style }) => {
  const location = useLocation();
  const defaultSelected = useMemo(() => {
    const type = (location as any).query.type;
    if (type === undefined) {
      return location.pathname;
    } else {
      return '/' + type;
    }
  }, [location]);
  const [selected, setSelected] = useState<string>(defaultSelected);
  const result = handleData(menuList);
  useEffect(() => {
    setSelected(defaultSelected);
  }, [location]);
  return (
    <MenuContext.Provider
      value={{
        curSelected: selected,
        setCurSelected: (selected: string) => {
          setSelected(selected);
        },
      }}
    >
      <Menu menuList={result} style={style} />
    </MenuContext.Provider>
  );
};
export default React.memo(BaseMenu);

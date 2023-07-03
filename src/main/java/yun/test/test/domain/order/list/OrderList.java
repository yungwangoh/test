package yun.test.test.domain.order.list;

import yun.test.test.domain.order.list.menu.Menu;

import java.util.List;

public interface OrderList {

    List<Menu> menuList(Menu... menus);
}

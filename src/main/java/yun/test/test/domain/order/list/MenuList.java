package yun.test.test.domain.order.list;

import lombok.NoArgsConstructor;
import yun.test.test.domain.order.list.menu.Menu;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class MenuList implements OrderList {

    @Override
    public List<Menu> menuList(Menu... menus) {

        return Arrays.asList(menus);
    }
}

package yun.test.test.domain.order.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yun.test.test.domain.order.Order;
import yun.test.test.domain.order.list.menu.*;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static yun.test.test.domain.order.list.menu.Caffeine.*;
import static yun.test.test.domain.order.list.menu.HotAndCold.*;
import static yun.test.test.domain.order.list.menu.MenuSize.*;

class OrderListTest {

    private OrderList orderList;
    private Order order;
    private Nutrients nutrients;

    public OrderListTest() {
        this.order = new Order(null,null);
        this.orderList = new MenuList();
    }

    @BeforeEach
    void init() {
        nutrients = new Nutrients(80, 10, 10, 10);
    }

    @Test
    void 여러_메뉴가_메뉴리스트에_들어간다() {
        // given
        Menu menu = new Beverage("자몽에이드", "자몽이 들어있는 에이드",
                Money.initialPrice(new BigDecimal("5500")), nutrients, BASIC);
        Menu menu2 = new Beverage("블루레몬에이드", "파란 레몬이 들어있는 에이드",
                Money.initialPrice(new BigDecimal("5500")), nutrients, BASIC);
        Menu menu3 = new Coffee("아메리카노", "과테말라산 원두를 쓴 커피",
                Money.initialPrice(new BigDecimal("6000")), nutrients, BASIC, HOT, CAFFEINE);


        // when
        List<Menu> menuList = orderList.menuList(menu, menu2, menu3);

        // then
        assertThat(menuList.size()).isEqualTo(3);
    }
}
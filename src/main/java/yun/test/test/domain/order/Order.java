package yun.test.test.domain.order;

import lombok.RequiredArgsConstructor;
import yun.test.test.domain.order.discount.condition.DiscountCondition;
import yun.test.test.domain.order.discount.policy.DiscountPolicy;
import yun.test.test.domain.order.list.OrderList;
import yun.test.test.domain.order.list.menu.Menu;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class Order {

    private final DiscountPolicy discountPolicy;
    private final OrderList orderList;

    public Money orderResult(User user, Menu... menus) {

        double discount = discountPolicy.calculateDiscount(user);

        List<Menu> menuList = fetchOrderList(menus);

        return calculateTotalPrice(menuList, discount, Money.initialPrice(new BigDecimal(0)));
    }

    private Money calculateTotalPrice(List<Menu> menuList, double discount, Money money) {

        for(var menu : menuList) {
            money.plus(menu.getPrice());
        }

        Money dc = Money.initialPrice(money.getTotalPrice());
        dc.discount(BigDecimal.valueOf(discount));

        money.minus(dc);

        return money;
    }

    private List<Menu> fetchOrderList(Menu... menus) {
        return orderList.menuList(menus);
    }
}

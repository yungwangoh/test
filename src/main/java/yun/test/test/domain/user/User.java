package yun.test.test.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yun.test.test.domain.delivery.Delivery;
import yun.test.test.domain.order.Order;
import yun.test.test.domain.order.list.menu.Menu;
import yun.test.test.domain.pay.Pay;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private UserRank userRank;
    private int orderCount;
    private LocalDateTime createAt;
    private Money money;
    private Order order;
    private Pay pay;
    private Delivery delivery;

    @Builder
    public User(int orderCount, LocalDateTime createAt, Money money, Order order, Pay pay, Delivery delivery) {
        this.userRank = UserRank.calculateUserRank(orderCount);
        this.orderCount = orderCount;
        this.createAt = createAt;
        this.money = money;
        this.order = order;
        this.pay = pay;
        this.delivery = delivery;
    }

    public Money order(Menu... menus) {
        if(menus.length > 0) {
            this.orderCount++;
        } else {
            throw new IllegalArgumentException("주문목록이 존재하지 않거나 비정상적인 값이 존재합니다.");
        }

        return order.orderResult(this, menus);
    }

    public void delivery() {
        this.delivery.delivery(this);
    }

    public void pay(Money paidMoney) {
        pay.pay(this, paidMoney);
    }

    public void payCharge(long charge) {
        this.money.plus(Money.initialPrice(new BigDecimal(charge)));
    }
}

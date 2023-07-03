package yun.test.test.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yun.test.test.domain.delivery.Address;
import yun.test.test.domain.delivery.Delivery;
import yun.test.test.domain.order.Order;
import yun.test.test.domain.order.discount.condition.RankCondition;
import yun.test.test.domain.order.discount.policy.PercentPolicy;
import yun.test.test.domain.order.list.MenuList;
import yun.test.test.domain.order.list.menu.Beverage;
import yun.test.test.domain.order.list.menu.Coffee;
import yun.test.test.domain.order.list.menu.Menu;
import yun.test.test.domain.order.list.menu.Nutrients;
import yun.test.test.domain.pay.NormalPay;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static yun.test.test.domain.order.list.menu.Caffeine.CAFFEINE;
import static yun.test.test.domain.order.list.menu.HotAndCold.HOT;
import static yun.test.test.domain.order.list.menu.MenuSize.BASIC;

class UserTest {

    private Order order;

    public UserTest() {
        this.order = new Order(new PercentPolicy(new RankCondition()), new MenuList());
    }

    private Menu menu;
    private Menu menu2;
    private Menu menu3;

    @BeforeEach
    void init() {
        Nutrients nutrients = new Nutrients(80, 10, 10, 10);

        menu = new Beverage("자몽에이드", "자몽이 들어있는 에이드",
                Money.initialPrice(new BigDecimal("5500")), nutrients, BASIC);
        menu2 = new Beverage("블루레몬에이드", "파란 레몬이 들어있는 에이드",
                Money.initialPrice(new BigDecimal("5500")), nutrients, BASIC);
        menu3 = new Coffee("아메리카노", "과테말라산 원두를 쓴 커피",
                Money.initialPrice(new BigDecimal("6000")), nutrients, BASIC, HOT, CAFFEINE);
    }

    @Test
    void 회원_등록_시간() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.of(2023, 6, 27, 4, 24))
                .money(Money.initialPrice(new BigDecimal(0)))
                .orderCount(0)
                .build();

        // when

        // then
        assertThat(user.getCreateAt()).isEqualTo(LocalDateTime.of(2023, 6, 27, 4, 24));
    }

    @Test
    void 회원이_현재_소유하고_있는_잔액() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(10000)))
                .orderCount(0)
                .build();

        // when
        Money money = Money.initialPrice(new BigDecimal(10000));

        // then
        assertThat(user.getMoney().getTotalPrice()).isEqualTo(money.getTotalPrice());
    }

    @Test
    void 회원이_주문한_개수() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(10000)))
                .orderCount(0)
                .build();

        // when
        for(int i = 0; i < 100; i++)
            user.order(menu);

        // then
        assertThat(user.getOrderCount()).isEqualTo(100);
    }

    @Test
    void 회원이_주문할때_주문한_메뉴가_없을_경우_에러를_발생시킨다() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(10000)))
                .orderCount(0)
                .build();

        // when

        // then
        assertThatThrownBy(user::order)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주문목록이 존재하지 않거나 비정상적인 값이 존재합니다.");
    }

    @Test
    void 회원이_주문을_한다_주문금액을_확인_한다() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(0)))
                .orderCount(0)
                .build();

        // when
        Money money = user.order(menu, menu2, menu3);

        // then
        assertThat(money.getTotalPrice()).isEqualTo("17000.0");
    }

    @Test
    void 회원이_주문하고_결제하고_현재_잔액_확인() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(50000)))
                .orderCount(0)
                .pay(new NormalPay())
                .build();

        // when
        Money money = user.order(menu, menu2, menu3);
        user.pay(money);

        // then
        assertThat(user.getMoney().getTotalPrice()).isEqualTo("33000.0");
    }

    @Test
    void 회원이_잔액을_충전한다() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(50000)))
                .orderCount(0)
                .pay(new NormalPay())
                .build();

        // when
        user.payCharge(50000);

        // then
        assertThat(user.getMoney().getTotalPrice()).isEqualTo("100000");
    }

    @Test
    void 회원이_주문한_물품들을_배달시킨다() {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(50000)))
                .orderCount(0)
                .pay(new NormalPay())
                .delivery(new Delivery(new Address("평택시", "동삭동", "샛길로")))
                .build();

        // when
        user.delivery();

        // then

    }
}
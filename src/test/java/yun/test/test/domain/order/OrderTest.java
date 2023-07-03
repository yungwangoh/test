package yun.test.test.domain.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import yun.test.test.domain.order.discount.condition.RankCondition;
import yun.test.test.domain.order.discount.policy.PercentPolicy;
import yun.test.test.domain.order.list.MenuList;
import yun.test.test.domain.order.list.menu.Beverage;
import yun.test.test.domain.order.list.menu.Coffee;
import yun.test.test.domain.order.list.menu.Menu;
import yun.test.test.domain.order.list.menu.Nutrients;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static yun.test.test.domain.order.list.menu.Caffeine.CAFFEINE;
import static yun.test.test.domain.order.list.menu.HotAndCold.HOT;
import static yun.test.test.domain.order.list.menu.MenuSize.BASIC;

class OrderTest {

    private Order order;

    public OrderTest() {
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

    @ParameterizedTest
    @CsvSource({"0, 17000.0", "1, 15300.0", "6, 14450.00", "11, 13600.0", "16, 11900.0"})
    void 주문한_메뉴들을_계산하여_반환한다(int orderCount, String moneyReturn) {
        // given
        User user = User.builder()
                .order(order)
                .createAt(LocalDateTime.now())
                .orderCount(orderCount)
                .money(Money.initialPrice(new BigDecimal(0)))
                .build();

        // when
        Money money = order.orderResult(user, menu, menu2, menu3);

        // then
        assertThat(money.getTotalPrice()).isEqualTo(moneyReturn);
    }
}
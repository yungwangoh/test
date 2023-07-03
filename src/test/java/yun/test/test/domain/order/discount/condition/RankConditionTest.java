package yun.test.test.domain.order.discount.condition;

import org.junit.jupiter.api.Test;
import yun.test.test.domain.order.Order;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RankConditionTest {

    private RankCondition rankCondition;
    private Order order;

    public RankConditionTest() {
        this.rankCondition = new RankCondition();
        this.order = new Order(null, null);
    }

    @Test
    void 유저의_랭크가_조건에_불충족하는지_확인() {
        // given
        User user = User.builder()
                .order(order)
                .orderCount(0)
                .money(Money.ZERO)
                .createAt(LocalDateTime.now())
                .build();

        // when
        boolean condition = rankCondition.isSatisfiedBy(user);

        // then
        assertFalse(condition);
    }

    @Test
    void 유저의_랭크가_조건에_충족하는지_확인() {
        // given
        User user = User.builder()
                .order(order)
                .orderCount(5)
                .money(Money.ZERO)
                .createAt(LocalDateTime.now())
                .build();

        // when
        boolean condition = rankCondition.isSatisfiedBy(user);

        // then
        assertTrue(condition);
    }
}
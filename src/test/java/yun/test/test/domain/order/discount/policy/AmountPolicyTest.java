package yun.test.test.domain.order.discount.policy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yun.test.test.domain.order.discount.condition.RankCondition;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AmountPolicyTest {

    private DiscountPolicy discountPolicy;

    public AmountPolicyTest() {
        this.discountPolicy = new AmountPolicy(new RankCondition());
    }

    @Test
    void 첫_주문_할인_일정_금액을_할인한다() {
        // given
        User user = User.builder()
                .order(null)
                .orderCount(0)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(0)))
                .build();

        // when
        double discount = discountPolicy.calculateDiscount(user);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    void 첫_주문이_아닌경우_첫_주문할인_적용_안됨() {
        // given
        User user = User.builder()
                .order(null)
                .orderCount(1)
                .createAt(LocalDateTime.now())
                .money(Money.initialPrice(new BigDecimal(0)))
                .build();

        // when
        double discount = discountPolicy.calculateDiscount(user);

        // then
        assertThat(discount).isEqualTo(0);
    }
}
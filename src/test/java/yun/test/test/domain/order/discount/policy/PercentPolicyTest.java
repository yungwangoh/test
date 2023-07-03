package yun.test.test.domain.order.discount.policy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import yun.test.test.domain.order.Order;
import yun.test.test.domain.order.discount.condition.RankCondition;
import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PercentPolicyTest {

    private final DiscountPolicy discountPolicy;
    private final Order order;

    public PercentPolicyTest() {
        this.discountPolicy = new PercentPolicy(new RankCondition());
        this.order = new Order(null, null);
    }

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 0.1", "6, 0.15", "11, 0.2", "16, 0.3"})
    void 할인_정책_등급_할인(int orderCount, double rankDiscountPercent) {
        // given
        User user = User.builder()
                .order(order)
                .orderCount(orderCount)
                .money(Money.ZERO)
                .createAt(LocalDateTime.now())
                .build();

        // when
        double percent = discountPolicy.calculateDiscount(user);

        // then
        assertThat(percent).isEqualTo(rankDiscountPercent);
    }
}
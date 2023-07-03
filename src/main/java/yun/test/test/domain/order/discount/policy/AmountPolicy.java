package yun.test.test.domain.order.discount.policy;

import lombok.RequiredArgsConstructor;
import yun.test.test.domain.order.discount.condition.DiscountCondition;
import yun.test.test.domain.user.User;

@RequiredArgsConstructor
public class AmountPolicy implements DiscountPolicy {

    private final DiscountCondition condition;

    @Override
    public double calculateDiscount(User user) {
        if(!condition.isSatisfiedBy(user)) {
            return 1000;
        }
        return 0;
    }
}

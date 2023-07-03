package yun.test.test.domain.order.discount.condition;

import yun.test.test.domain.user.User;

public interface DiscountCondition {

    boolean isSatisfiedBy(User user);
}

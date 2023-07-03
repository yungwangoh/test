package yun.test.test.domain.order.discount.policy;

import yun.test.test.domain.user.User;

public interface DiscountPolicy {

    double calculateDiscount(User user);
}

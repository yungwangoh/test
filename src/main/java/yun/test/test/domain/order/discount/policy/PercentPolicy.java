package yun.test.test.domain.order.discount.policy;

import lombok.RequiredArgsConstructor;
import yun.test.test.domain.order.discount.condition.DiscountCondition;
import yun.test.test.domain.user.User;
import yun.test.test.domain.user.UserRank;

@RequiredArgsConstructor
public class PercentPolicy implements DiscountPolicy {

    private final DiscountCondition condition;

    @Override
    public double calculateDiscount(User user) {
        if(condition.isSatisfiedBy(user)) {
            return calculateDiscountByUserRank(user.getUserRank());
        }
        return 0;
    }

    private double calculateDiscountByUserRank(UserRank userRank) {

        if(userRank.equals(UserRank.SILVER)) {
            return 0.1;
        } else if(userRank.equals(UserRank.GOLD)) {
            return 0.15;
        } else if(userRank.equals(UserRank.PLATINUM)) {
            return 0.2;
        } else if(userRank.equals(UserRank.DIAMOND)) {
            return 0.3;
        } else {
            throw new IllegalArgumentException("유저 랭크가 만족하지 않습니다.");
        }
    }
}

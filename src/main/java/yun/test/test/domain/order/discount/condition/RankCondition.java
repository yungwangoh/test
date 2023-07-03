package yun.test.test.domain.order.discount.condition;

import yun.test.test.domain.user.User;
import yun.test.test.domain.user.UserRank;

public class RankCondition implements DiscountCondition {

    // 할인 혜택은 실버 등급 이상만 들어간다.
    @Override
    public boolean isSatisfiedBy(User user) {
        return checkCondition(user.getUserRank(), UserRank.SILVER) || checkCondition(user.getUserRank(), UserRank.GOLD) ||
                checkCondition(user.getUserRank(), UserRank.PLATINUM) || checkCondition(user.getUserRank(), UserRank.DIAMOND);
    }

    private boolean checkCondition(UserRank userRank, UserRank conditionRank) {
        return userRank == conditionRank;
    }
}

package yun.test.test.domain.pay;

import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

public interface Pay {

    void pay(User user, Money paidMoney);
}

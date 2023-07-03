package yun.test.test.domain.pay;

import yun.test.test.domain.user.Money;
import yun.test.test.domain.user.User;

import java.math.BigDecimal;

public class NormalPay implements Pay {

    @Override
    public void pay(User user, Money paidMoney) {
        if(user.getMoney().getTotalPrice().equals(BigDecimal.valueOf(0))) {
            throw new IllegalStateException("잔액이 부족합니다. 충전 후 사용하세요");
        }

        user.getMoney().minus(paidMoney);
    }
}

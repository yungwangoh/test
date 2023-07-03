package yun.test.test.domain.order.list.menu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CoffeeTest {

    @Test
    void 불변객체는_assert_error() {
        assertThatThrownBy(() -> new Coffee(null ,null, null, null,null,null,null))
                .isInstanceOf(AssertionError.class);
    }

}
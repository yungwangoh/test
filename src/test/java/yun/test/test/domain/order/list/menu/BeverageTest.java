package yun.test.test.domain.order.list.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeverageTest {

    @Test
    void 불변객체는_assert_error() {
        assertThatThrownBy(() -> new Beverage(null, null ,null,null ,null))
                .isInstanceOf(AssertionError.class);
    }
}
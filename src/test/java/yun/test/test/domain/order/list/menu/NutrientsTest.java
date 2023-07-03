package yun.test.test.domain.order.list.menu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NutrientsTest {

    @Test
    void 불변객체는_assert_error() {
        assertThatThrownBy(() -> new Nutrients(-1, -1, -1, -1))
                .isInstanceOf(AssertionError.class);
    }

}
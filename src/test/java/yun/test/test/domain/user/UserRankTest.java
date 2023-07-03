package yun.test.test.domain.user;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UserRankTest {

    @ParameterizedTest
    @CsvSource({"0, BRONZE", "1, SILVER", "6, GOLD", "11, PLATINUM", "16, DIAMOND"})
    void 주문_개수_기준으로_등급_승급(int orderCount, UserRank rank) {
        // given
        User user = User.builder()
                .orderCount(orderCount)
                .money(null)
                .createAt(null)
                .order(null)
                .build();

        // when
        UserRank userRank = user.getUserRank();

        // then
        assertThat(userRank).isEqualTo(rank);
    }
}
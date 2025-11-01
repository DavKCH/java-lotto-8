package lotto.validate;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoBuyValidateTest {
    @Test
    void 금액이_1000원_나누어_떨어지지_않으면_예외가_발생_한다() {
        int cash = Randoms.pickNumberInRange(1001, 1999);

        assertThatThrownBy(() -> LottoBuyValidate.start(cash))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
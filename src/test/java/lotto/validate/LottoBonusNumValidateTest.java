package lotto.validate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class LottoBonusNumValidateTest {
    int[] winNumbers = {1, 2, 3, 4, 5, 6};

    @Test
    void 보너스번호가_NULL_이면_예외가_발생_한다() {
        String bonusNumStrInput = null;

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호가_비어_있으면_예외가_발생_한다() {
        String bonusNumStrInput = "";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호가_숫자가_아니면_예외가_발생_한다() {
        String bonusNumStrInput1 = "a";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput1, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        String bonusNumStrInput2 = "1a";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput2, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        String bonusNumStrInput3 = "1!2";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput3, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호가_1에서_45사이가_아니면_예외가_발생_한다() {
        String bonusNumStrInput1 = "46";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput1, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        String bonusNumStrInput2 = "0";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput2, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스번호와_당첨번호가_같으면_예외가_발생_한다() {
        // 당첨번호 1,2,3,4,5,6

        String bonusNumStrInput1 = "1";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput1, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);

        String bonusNumStrInput2 = "4";

        assertThatThrownBy(() -> LottoBonusNumValidate.start(bonusNumStrInput2, winNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
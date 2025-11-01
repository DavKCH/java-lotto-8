package lotto.validate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoWinNumValidateTest {

    @Test
    void 당첨번호가_NULL_이면_예외가_발생_한다() {
        String winNumStrInput = null;

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호가_비어_있으면_예외가_발생_한다() {
        String winNumStrInput = "";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_처음과_마지막이_컴마면_예외가_발생_한다() {
        String winNumStrInput1 = ",1";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String winNumStrInput2 = "2,";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호_처음과_마지막_사이에_공백이면_예외가_발생_한다() {
        String winNumStrInput1 = "1,2,3,4, ,6";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String winNumStrInput2 = "1, ,3,4,5,6";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호가_숫자가_아니면_예외가_발생_한다() {
        String winNumStrInput1 = "!,23,4";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String winNumStrInput2 = "5,7,9,:";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput2))
                .isInstanceOf(IllegalArgumentException.class);

        String winNumStrInput3 = "5,7,ㅁ,8";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호는_중복된_번호가_있으면_예외가_발생_한다() {
        String winNumStrInput = "1,2,3,4,5,5";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨번호는_총_6개가_아니면_예외가_발생_한다() {
        String winNumStrInput1 = "1,2,3,4,5";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String winNumStrInput2 = "1,2,3,4,5,6,7";

        assertThatThrownBy(() -> LottoWinNumValidate.start(winNumStrInput2))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
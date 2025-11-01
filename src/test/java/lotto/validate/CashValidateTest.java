package lotto.validate;


import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CashValidateTest {

    @Test
    void 구입금액이_NULL_이면_예외가_발생_한다() {
        String cashStrInput = null;

        assertThatThrownBy(() -> CashValidate.start(cashStrInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액이_비어_있으면_예외가_발생_한다() {
        String cashStrInput = "";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액이_0_으로_시작_하면_예외가_발생_한다() {
        String cashStrInput1 = "0";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String cashStrInput2 = "0123";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput2))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 구입금액이_숫자가_아니면_예외가_발생_한다() {
        String cashStrInput1 = "112,34";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String cashStrInput2 = "1a";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput2))
                .isInstanceOf(IllegalArgumentException.class);

        String cashStrInput3 = "11a1";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입금액이_999원_이하이거나_50000원을_넘으면_예외가_발생_한다() {
        String cashStrInput1 = "999";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput1))
                .isInstanceOf(IllegalArgumentException.class);

        String cashStrInput2 = "50001";

        assertThatThrownBy(() -> CashValidate.start(cashStrInput2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
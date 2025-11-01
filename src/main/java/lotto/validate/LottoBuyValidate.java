package lotto.validate;

import lotto.LottoRegistry;
import lotto.exception.LottoErrorException;

import static lotto.LottoAmount.PRICE;

public abstract class LottoBuyValidate {
    private LottoBuyValidate() {
    }

    private static void lottoBuyValidate(int cash) {

        double decimalPoint  = (double) cash / PRICE.getAmount();

        if (decimalPoint % 1 != 0) {
            throw new LottoErrorException("구입 금액은 " + PRICE.getAmount() + "원 단위로 구매해 주세요.");
        }
    }

    public static void start(int cash) {
        lottoBuyValidate(cash);

        double decimalPoint  = (double) cash / PRICE.getAmount();
        int lottoBuyCount = (int) decimalPoint;

        LottoRegistry.setLottoBuyCount(lottoBuyCount);
    }

}

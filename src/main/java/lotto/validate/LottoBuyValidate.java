package lotto.validate;

import lotto.LottoRegistry;
import static lotto.LottoAmount.PRICE;

public abstract class LottoBuyValidate {
    private LottoBuyValidate() {
    }

    private static void lottoBuyValidate(int cash) {

        double decimalPoint  = (double) cash / PRICE.getAmount();

        if (decimalPoint % 1 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void start(int cash) {
        lottoBuyValidate(cash);

        double decimalPoint  = (double) cash / PRICE.getAmount();
        int lottoBuyCount = (int) decimalPoint;

        LottoRegistry.setLottoBuyCount(lottoBuyCount);
    }

}

package lotto.validate;

import lotto.LottoRegistry;

public abstract class LottoBuyValidate {

    private LottoBuyValidate() {
    }

    private static void lottoBuyValidate(int cash) {

        double decimalPoint  = cash / 1000.0;

        if (decimalPoint % 1 != 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void start(int cash) {
        lottoBuyValidate(cash);

        double decimalPoint  = cash / 1000.0;
        int lottoBuyCount = (int) decimalPoint;

        LottoRegistry.setLottoBuyCount(lottoBuyCount);
    }

}

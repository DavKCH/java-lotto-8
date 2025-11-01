package lotto.validate;

import lotto.LottoRegistry;

public abstract class CashValidate {


    private static void cashNullOrEmpty(String cashStrInput) {

        if (cashStrInput == null) {
            throw new IllegalArgumentException();
        }

        cashStrInput = cashStrInput.trim();
        if (cashStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }

    }

    private static void cashStartFalse(String cashStrInput) {

        if (cashStrInput.startsWith("0")) {
            throw new IllegalArgumentException();
        }
    }

    private static void cashNotInteger(String cashStrInput) {

        String trueStr = "^[0-9]*$";
        if (!cashStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }
    }

    private static void cashRangeFalse(String cashStrInput) {
        int cash = Integer.parseInt(cashStrInput);

        if (cash < 1000 ) {
            throw new IllegalArgumentException();
        }

        if (cash > 50001) {
            throw new IllegalArgumentException();
        }

    }

    public static void start(String cashStrInput) {

        cashNullOrEmpty(cashStrInput);
        cashStartFalse(cashStrInput);
        cashNotInteger(cashStrInput);
        cashRangeFalse(cashStrInput);

        int cash = Integer.parseInt(cashStrInput);
        LottoRegistry.setCash(cash);
    }

}

package lotto.validate;

import lotto.LottoRegistry;
import lotto.exception.LottoErrorException;

import static lotto.LottoAmount.LIMIT_PRICE;
import static lotto.LottoAmount.PRICE;

public abstract class CashValidate {

    private static void cashNullOrEmpty(String cashStrInput) {

        if (cashStrInput == null) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }

        cashStrInput = cashStrInput.trim();
        if (cashStrInput.isEmpty()) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }

    }

    private static void cashStartFalse(String cashStrInput) {

        if (cashStrInput.startsWith("0")) {
            throw new LottoErrorException("처음에 '0'을 입력 하실수 없습니다.");
        }
    }

    private static void cashNotInteger(String cashStrInput) {

        String trueStr = "^[0-9]*$";
        if (!cashStrInput.matches(trueStr)) {
            throw new LottoErrorException("숫자만 가능 합니다.");
        }
    }

    private static void cashRangeFalse(String cashStrInput) {

        int cash = Integer.parseInt(cashStrInput);

        if (cash < 1000 ) {
            throw new LottoErrorException("로또 가격은 " + PRICE.getAmount() + "원 입니다.");
        }

        if (cash > 50001) {
            throw new LottoErrorException("로또 최대 구매 횟수는 " + LIMIT_PRICE.getAmount() + "원 입니다.");
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

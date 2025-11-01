package lotto.validate;

import lotto.LottoRegistry;

public abstract class LottoBonusNumValidate {

    private static void bonusNumberNullOrEmpty(String bonusNumStrInput) {

        if (bonusNumStrInput == null) {
            throw new IllegalArgumentException();
        }

        bonusNumStrInput = bonusNumStrInput.trim();
        if (bonusNumStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static void bonusNumberInputFalse(String bonusNumStrInput) {
        if (bonusNumStrInput.startsWith("0")) {
            throw new IllegalArgumentException();
        }

        String trueStr = "^[0-9]*$";
        if (!bonusNumStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }
    }

    private static void bonusNumberRangeFalse(String bonusNumStrInput) {
        int bonusNumber = Integer.parseInt(bonusNumStrInput);

        if (!(bonusNumber >= 1 && bonusNumber <= 45)) {
            throw new IllegalArgumentException();
        }
    }

    private static void bonusNumWinNumDuplicate(String bonusNumStrInput, int[] winNumbers) {
        int bonusNumber = Integer.parseInt(bonusNumStrInput);

        for (int winNumber : winNumbers) {
            if (winNumber == bonusNumber) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void start(String bonusNumStrInput, int[] winNumbers) {
        bonusNumberNullOrEmpty(bonusNumStrInput);
        bonusNumberInputFalse(bonusNumStrInput);
        bonusNumberRangeFalse(bonusNumStrInput);
        bonusNumWinNumDuplicate(bonusNumStrInput, winNumbers);

        int bonusNumber = Integer.parseInt(bonusNumStrInput);
        LottoRegistry.setBonusNumber(bonusNumber);
    }


}

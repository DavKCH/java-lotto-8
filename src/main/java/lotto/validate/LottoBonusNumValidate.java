package lotto.validate;

import lotto.LottoRegistry;
import lotto.exception.LottoErrorException;

public abstract class LottoBonusNumValidate {

    private static void bonusNumberNullOrEmpty(String bonusNumStrInput) {

        if (bonusNumStrInput == null) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }

        bonusNumStrInput = bonusNumStrInput.trim();
        if (bonusNumStrInput.isEmpty()) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }
    }

    private static void bonusNumberInputFalse(String bonusNumStrInput) {
        if (bonusNumStrInput.startsWith("0")) {
            throw new LottoErrorException("처음에 '0'을 입력 하실수 없습니다.");
        }

        String trueStr = "^[0-9]*$";
        if (!bonusNumStrInput.matches(trueStr)) {
            throw new LottoErrorException("숫자만 가능 합니다.");
        }
    }

    private static void bonusNumberRangeFalse(String bonusNumStrInput) {
        int bonusNumber = Integer.parseInt(bonusNumStrInput);

        if (!(bonusNumber >= 1 && bonusNumber <= 45)) {
            throw new LottoErrorException("보너스 번호는 1~45 사이의 숫자만 가능 합니다.");
        }
    }

    private static void bonusNumWinNumDuplicate(String bonusNumStrInput, int[] winNumbers) {
        int bonusNumber = Integer.parseInt(bonusNumStrInput);

        for (int winNumber : winNumbers) {
            if (winNumber == bonusNumber) {
                throw new LottoErrorException("당첨 번호 와 보너스 번호는 중복 불가능 입니다.");
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

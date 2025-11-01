package lotto.validate;

import lotto.LottoRegistry;
import lotto.exception.LottoErrorException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class LottoWinNumValidate {

    private static final String DELIMITER = ",";
    private static final int WIN_NUMBER_COUNT = 6;

    private static void winNumbersNullOrEmpty(String winNumStrInput) {

        if (winNumStrInput == null) {
            throw new LottoErrorException("잘못된 값을 입력 하셨습니다");
        }

        winNumStrInput = winNumStrInput.trim();
        if (winNumStrInput.isEmpty()) {
            throw new LottoErrorException("잘못된 값을 입력 하셨습니다");
        }
    }

    private static void winNumberStartOrLastFalse (String winNumStrInput) {
        if (winNumStrInput.startsWith(DELIMITER)) {
            throw new LottoErrorException("처음에 ','을 입력 하실 수 없습니다.");
        }

        String lastWinNumInput = winNumStrInput.substring(winNumStrInput.length()-1);
        if (lastWinNumInput.equals(DELIMITER)) {
            throw new LottoErrorException("마지막에 ','을 입력 하실 수 없습니다.");
        }
    }


    private static void winNumberInputFalse(String winNumStrInput) {

        String[] winNumStr = winNumStrInput.split(DELIMITER);
        for (String strNum : winNumStr) {
            String trimNum = strNum.trim();
            if (trimNum.isEmpty()) {
                throw new LottoErrorException("중간에 공백은 불가능 합니다.");
            }
        }

        String trueStr = "^[0-9,]*$";
        if (!winNumStrInput.matches(trueStr)) {
            throw new LottoErrorException("숫자만 가능 합니다.");
        }
    }

    private static void winNumberDuplicate(String winNumStrInput) {
        String[] strNumbers = winNumStrInput.split(DELIMITER);

        List<String> numbers = Arrays.asList(strNumbers);
        HashSet<String> hashSet = new HashSet<>(numbers);
        if (numbers.size() != hashSet.size()) {
            throw new LottoErrorException("당첨 번호는 중복 불가능 입니다.");
        }
    }

    private static void winNumberLengthFalse(String winNumStrInput) {
        String[] strNumbers = winNumStrInput.split(DELIMITER);

        if (strNumbers.length != WIN_NUMBER_COUNT) {
            throw new LottoErrorException("당첨 번호는 총 "+ WIN_NUMBER_COUNT + "개를 입력 하셔야 합니다.");
        }
    }

    private static void winNumberRangeFalse(String winNumStrInput) {
        int[] winNumbers = getNumbers(winNumStrInput);

        for (int winNumber : winNumbers) {
            if (!(winNumber >= 1 && winNumber <= 45)) {
                throw new LottoErrorException("당첨 번호는 1~45 사이의 숫자만 가능 합니다.");
            }
        }
    }

    private static int[] getNumbers(String winNumStrInput) {
        String[] strNumbers = winNumStrInput.split(DELIMITER);
        int[] winNumbers = new int[WIN_NUMBER_COUNT];

        for (int i = 0; i < WIN_NUMBER_COUNT; i++) {
            winNumbers[i] = Integer.parseInt(strNumbers[i]);
        }
        return winNumbers;
    }

    public static void start(String winNumStrInput) {
        winNumbersNullOrEmpty(winNumStrInput);

        winNumberStartOrLastFalse(winNumStrInput);
        winNumberInputFalse(winNumStrInput);

        winNumberDuplicate(winNumStrInput);

        winNumberLengthFalse(winNumStrInput);
        winNumberRangeFalse(winNumStrInput);

        int[] winNumbers = getNumbers(winNumStrInput);
        LottoRegistry.setWinNumbers(winNumbers);
    }


}

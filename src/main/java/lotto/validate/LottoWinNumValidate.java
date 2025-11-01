package lotto.validate;

import lotto.LottoRegistry;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class LottoWinNumValidate {

    private static final String DELIMITER = ",";
    private static final int WIN_NUMBER_COUNT = 6;

    private static void winNumbersNullOrEmpty(String winNumStrInput) {

        if (winNumStrInput == null) {
            throw new IllegalArgumentException();
        }

        winNumStrInput = winNumStrInput.trim();
        if (winNumStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private static void winNumberStartOrLastFalse (String winNumStrInput) {
        if (winNumStrInput.startsWith(DELIMITER)) {
            throw new IllegalArgumentException();
        }

        String lastWinNumInput = winNumStrInput.substring(winNumStrInput.length()-1);
        if (lastWinNumInput.equals(DELIMITER)) {
            throw new IllegalArgumentException();
        }
    }


    private static void winNumberInputFalse(String winNumStrInput) {

        String[] winNumStr = winNumStrInput.split(DELIMITER);
        for (String strNum : winNumStr) {
            String trimNum = strNum.trim();
            if (trimNum.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }

        String trueStr = "^[0-9,]*$";
        if (!winNumStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }
    }

    private static void winNumberDuplicate(String winNumStrInput) {
        String[] strNumbers = winNumStrInput.split(DELIMITER);

        List<String> numbers = Arrays.asList(strNumbers);
        HashSet<String> hashSet = new HashSet<>(numbers);
        if (numbers.size() != hashSet.size()) {
            throw new IllegalArgumentException();
        }
    }

    private static void winNumberLengthFalse(String winNumStrInput) {
        String[] strNumbers = winNumStrInput.split(DELIMITER);

        if (strNumbers.length != WIN_NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private static void winNumberRangeFalse(String winNumStrInput) {
        int[] winNumbers = getNumbers(winNumStrInput);

        for (int winNumber : winNumbers) {
            if (!(winNumber >= 1 && winNumber <= 45)) {
                throw new IllegalArgumentException();
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

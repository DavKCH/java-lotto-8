package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Application {

    private static final LottoRepository lottoRepository = new LottoRepository();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        String cashStrInput = Console.readLine();
        int cash = cashValidate(cashStrInput);

        int lottoBuyCount = lottoBuyValidate(cash);
        for (int i = 0; i < lottoBuyCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted().toList();

            lottoRepository.add(new Lotto(numbers));
        }
        lottoRepository.lottoCatalog();

        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumStrInput = Console.readLine();

        int[] winNumbers = lottoWinNumValidate(winNumStrInput);

        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumStrInput = Console.readLine();
        int bonusNumber = lottoBonusNumValidate(bonusNumStrInput, winNumbers);


        LottoGame lottoGame = new LottoGame(lottoBuyCount, winNumbers, bonusNumber, lottoRepository.getLottoList());
        lottoGame.gameStart();
    }

    private static int cashValidate(String cashStrInput) {

        if (cashStrInput == null) {
            throw new IllegalArgumentException();
        }

        cashStrInput = cashStrInput.trim();
        if (cashStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (cashStrInput.startsWith("0")) {
            throw new IllegalArgumentException();
        }

        String trueStr = "^[0-9]*$";
        if (!cashStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }

        int cash = Integer.parseInt(cashStrInput);

        if (cash < 1000 ) {
            throw new IllegalArgumentException();
        }

        if (cash > 50001) {
            throw new IllegalArgumentException();
        }
        System.out.println();

        return cash;
    }

    private static int lottoBuyValidate(int cash) {

        double decimalPoint  = cash / 1000.0;

        if (decimalPoint % 1 != 0) {
            throw new IllegalArgumentException();
        }

        int lottoBuyCount = (int) decimalPoint;
        return lottoBuyCount;
    }

    private static int[] lottoWinNumValidate(String winNumStrInput) {
        final String DELIMITER = ",";
        final int WIN_NUMBER_COUNT = 6;

        if (winNumStrInput == null) {
            throw new IllegalArgumentException();
        }

        winNumStrInput = winNumStrInput.trim();
        if (winNumStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (winNumStrInput.startsWith(DELIMITER)) {
            throw new IllegalArgumentException();
        }

        String lastWinNumInput = winNumStrInput.substring(winNumStrInput.length()-1);
        if (lastWinNumInput.equals(DELIMITER)) {
            throw new IllegalArgumentException();
        }

        String[] winNumStr = winNumStrInput.split(DELIMITER);
        for (String strNum : winNumStr) {
            String trimNum = strNum.trim();
            if (trimNum.isEmpty()) {
                throw new IllegalArgumentException();
            }
        }

        String trueStr = "^[0-9]*$";
        if (winNumStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }

        String[] strNumbers = winNumStrInput.split(DELIMITER);
        List<String> numbers = Arrays.asList(strNumbers);
        HashSet<String> hashSet = new HashSet<>(numbers);
        if (numbers.size() != hashSet.size()) {
            throw new IllegalArgumentException();
        }

        if (strNumbers.length != WIN_NUMBER_COUNT) {
            throw new IllegalArgumentException();
        }

        int[] winNumbers = new int[WIN_NUMBER_COUNT];

        for (int i = 0; i < WIN_NUMBER_COUNT; i++) {
            winNumbers[i] = Integer.parseInt(strNumbers[i]);
        }

        for (int winNumber : winNumbers) {
            if (winNumber >= 46) {
                throw new IllegalArgumentException();
            }
        }

        System.out.println();

        return winNumbers;
    }

    private static int lottoBonusNumValidate(String bonusNumStrInput, int[] winNumbers) {
        if (bonusNumStrInput == null) {
            throw new IllegalArgumentException();
        }

        bonusNumStrInput = bonusNumStrInput.trim();
        if (bonusNumStrInput.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (bonusNumStrInput.startsWith("0")) {
            throw new IllegalArgumentException();
        }

        String trueStr = "^[0-9]*$";
        if (!bonusNumStrInput.matches(trueStr)) {
            throw new IllegalArgumentException();
        }

        int bonusNumber = Integer.parseInt(bonusNumStrInput);

        if (bonusNumber >= 46) {
            throw new IllegalArgumentException();
        }

        for (int winNumber : winNumbers) {
            if (winNumber == bonusNumber) {
                throw new IllegalArgumentException();
            }
        }
        System.out.println();

        return bonusNumber;
    }

}

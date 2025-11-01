package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import lotto.validate.CashValidate;
import lotto.validate.LottoBuyValidate;
import lotto.validate.LottoWinNumValidate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Application {

    private static final LottoRepository lottoRepository = new LottoRepository();

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해 주세요.");
        String cashStrInput = Console.readLine();
        CashValidate.start(cashStrInput);
        int cash = LottoRegistry.getCash();

        LottoBuyValidate.start(cash);
        System.out.println();

        int lottoBuyCount = LottoRegistry.getLottoBuyCount();
        for (int i = 0; i < lottoBuyCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted().toList();

            lottoRepository.add(new Lotto(numbers));
        }
        lottoRepository.lottoCatalog();

        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumStrInput = Console.readLine();
        LottoWinNumValidate.start(winNumStrInput);

        int[] winNumbers = LottoRegistry.getWinNumbers();
        System.out.println();

        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumStrInput = Console.readLine();
        int bonusNumber = lottoBonusNumValidate(bonusNumStrInput, winNumbers);


        LottoGame lottoGame = new LottoGame(lottoBuyCount, winNumbers, bonusNumber, lottoRepository.getLottoList());
        lottoGame.gameStart();
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

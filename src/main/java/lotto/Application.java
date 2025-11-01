package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import lotto.validate.CashValidate;
import lotto.validate.LottoBonusNumValidate;
import lotto.validate.LottoBuyValidate;
import lotto.validate.LottoWinNumValidate;

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
        lottoBuyLogic(lottoBuyCount);

        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumStrInput = Console.readLine();
        LottoWinNumValidate.start(winNumStrInput);

        int[] winNumbers = LottoRegistry.getWinNumbers();
        System.out.println();

        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumStrInput = Console.readLine();
        LottoBonusNumValidate.start(bonusNumStrInput, winNumbers);
        System.out.println();

        int bonusNumber = LottoRegistry.getBonusNumber();

        LottoGame lottoGame = new LottoGame(lottoBuyCount, winNumbers, bonusNumber, lottoRepository.getLottoList());
        lottoGame.gameStart();
    }

    private static void lottoBuyLogic(int lottoBuyCount) {
        for (int i = 0; i < lottoBuyCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
                    .stream()
                    .sorted().toList();

            lottoRepository.add(new Lotto(numbers));
        }
        lottoRepository.lottoCatalog();
    }


}

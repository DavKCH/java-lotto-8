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

        logicStart(()-> buyAmountLogic());
        logicStart(()-> winNumberLogic());
        logicStart(()-> bonusNumLogic());
        logicStart(()-> gameLogic());

    }

    private static void logicStart(Runnable logic) {

        while (true) {
            try {
                logic.run();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    // ======== buyAmountLogic Start ========
    private static void buyAmountLogic() {
        System.out.println("구입금액을 입력해 주세요.");
        String cashStrInput = Console.readLine();
        CashValidate.start(cashStrInput);
        int cash = LottoRegistry.getCash();
        LottoBuyValidate.start(cash);
        System.out.println();

        int lottoBuyCount = LottoRegistry.getLottoBuyCount();
        lottoBuyLogic(lottoBuyCount);
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
    // ======== buyAmountLogic END ========

    
    private static void winNumberLogic() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String winNumStrInput = Console.readLine();
        LottoWinNumValidate.start(winNumStrInput);

        System.out.println();
    }

    private static void bonusNumLogic() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String bonusNumStrInput = Console.readLine();
        LottoBonusNumValidate.start(bonusNumStrInput, LottoRegistry.getWinNumbers());
        System.out.println();
    }

    private static void gameLogic() {
        int lottoBuyCount = LottoRegistry.getLottoBuyCount();
        int[] winNumbers = LottoRegistry.getWinNumbers();
        int bonusNumber = LottoRegistry.getBonusNumber();
        List<Lotto> lottoList = lottoRepository.getLottoList();

        LottoGame lottoGame = new LottoGame(lottoBuyCount, winNumbers, bonusNumber, lottoList);
        lottoGame.gameStart();
    }

    
}

package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

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

}

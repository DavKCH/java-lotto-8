package lotto;

import java.util.ArrayList;
import java.util.List;

import static lotto.LottoAmount.*;

public class LottoGame {

    // 로또 구매 횟수
    private final int gameCount;
    // 당첨 번호
    private final int[] winNumbers;
    // 보너스 번호
    private final int bonusNumber;

    // 로또 용지들
    private final List<Lotto> lottoList;

    // 구매 용지 마다, 당첨 번호 및 보너스 번호가 몇개씩 있는지 확인
    private final List<Integer> winNumberCount = new ArrayList<>();
    private final List<Integer> bonusNumberCount = new ArrayList<>();


    // 당첨 횟수 카운트
    private int rankFiveCount;
    private int rankFourCount;
    private int rankThreeCount;
    private int rankTwoCount;
    private int rankOneCount;

    // 수익률
    private double amountRate;


    public LottoGame(int gameCount, int[] winNumbers, int bonusNumber, List<Lotto> lottoList) {
        this.gameCount = gameCount;
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;

        this.lottoList = lottoList;
    }


    private void winNumberStatus() {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (%,d원) - %d개\n", RANK_5.getAmount(), rankFiveCount);
        System.out.printf("4개 일치 (%,d원) - %d개\n", RANK_4.getAmount(), rankFourCount);
        System.out.printf("5개 일치 (%,d원) - %d개\n", RANK_3.getAmount(), rankThreeCount);
        System.out.printf("5개 일치, 보너스 볼 일치 (%,d원) - %d개\n", RANK_2.getAmount(), rankTwoCount);
        System.out.printf("6개 일치 (%,d원) - %d개\n", RANK_1.getAmount(), rankOneCount);
        System.out.printf("총 수익률은 %.1f%%입니다.", amountRate);
    }

    public void gameStart() {
        matchNumber();
        totalBallMatch();
        amountSum();

        winNumberStatus();
    }

    // ======== match Number Start ========
    private void matchNumber() {
        // 당첨 번호 체크
        for (Lotto lotto : lottoList) {
            int count = 0;
            for (int i = 0; i < winNumbers.length; i++) {
                count += winNumberCheck(lotto, i);
            }
            winNumberCount.add(count);
        }
        // 보너스 번호 체크
        for (Lotto lotto : lottoList) {
            int bonusCount = bonusNumberCheck(lotto);
            bonusNumberCount.add(bonusCount);
        }
    }

    private int winNumberCheck(Lotto lotto, int i) {

        boolean hasWinNumber = lotto.getNumbers().contains(winNumbers[i]);

        if (hasWinNumber) {
            return 1;
        }
        return 0;
    }

    private int bonusNumberCheck(Lotto lotto) {

        boolean hasBonusNumber = lotto.getNumbers().contains(bonusNumber);

        if (hasBonusNumber) {
            return 1;
        }
        return 0;
    }
    // ======== match Number END ========



    // ======== totalBallMatch Start ========
    private void totalBallMatch () {
        for (int i = 0; i < gameCount; i++) {
            ballMatch(i);
        }
    }

    private void ballMatch(int i) {

        if (winNumberCount.get(i) == 6) { 
            rankOneCount++;
        }

        if ((winNumberCount.get(i) == 5) && (bonusNumberCount.get(i)) != 0) {
            rankTwoCount++;
        }

        if (winNumberCount.get(i) == 5) {
            rankThreeCount++;
        }

        if (winNumberCount.get(i) == 4) {
            rankFourCount++;
        }
        
        if (winNumberCount.get(i) == 3) {
            rankFiveCount++;
        }
    }
    // ======== totalBallMatch END ========


    // ======== amountSum Start ========
    private void amountSum() {
        int lastAmount = (RANK_1.getAmount() * rankOneCount) + (RANK_2.getAmount() * rankTwoCount)
                + (RANK_3.getAmount() * rankThreeCount) + (RANK_4.getAmount() * rankFourCount) + (RANK_5.getAmount() * rankFiveCount);
        
        int originalAmount = PRICE.getAmount() * lottoList.size();

        //수익률
        amountRate = ((double) lastAmount / originalAmount) * 100.0;
    }
    // ======== amountSum END ========

}

package lotto;

public class LottoRegistry {

    private static int cash;
    private static int lottoBuyCount;
    private static int[] winNumbers;

    private LottoRegistry() {
    }

    public static void setCash(int cash) {
        LottoRegistry.cash = cash;
    }

    public static int getCash() {
        return cash;
    }

    public static void setLottoBuyCount(int lottoBuyCount) {
        LottoRegistry.lottoBuyCount = lottoBuyCount;
    }

    public static int getLottoBuyCount() {
        return lottoBuyCount;
    }

    public static void setWinNumbers(int[] winNumbers) {
        LottoRegistry.winNumbers = winNumbers;
    }
    public static int[] getWinNumbers() {
        return winNumbers;
    }
}

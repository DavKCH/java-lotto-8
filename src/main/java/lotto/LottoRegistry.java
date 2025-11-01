package lotto;

public class LottoRegistry {

    private static int cash;
    private static int lottoBuyCount;

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
}

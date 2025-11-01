package lotto;

public enum LottoAmount {

    RANK_1(2_000_000_000),
    RANK_2(30_000_000),
    RANK_3(1_500_000),
    RANK_4(50_000),
    RANK_5(5_000),
    PRICE(1_000),
    LIMIT_PRICE(50_000);

    private final int amount;

    LottoAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

}

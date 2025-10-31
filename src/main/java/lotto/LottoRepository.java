package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {

    private final List<Lotto> lottoList = new ArrayList<>();

    public void add(Lotto lotto) {
        lottoList.add(lotto);
    }

    public List<Lotto> getLottoList() {
        return lottoList;
    }

    public void lottoCatalog() {
        System.out.println( lottoList.size() + "개를 구매했습니다.");
        for (int i = 0; i < lottoList.size(); i++) {
            List<Integer> numbers = lottoList.get(i).getNumbers();
            System.out.println(numbers);
        }
        System.out.println();
    }
}

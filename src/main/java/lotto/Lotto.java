package lotto;

import lotto.exception.LottoErrorException;

import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNullOrEmpty(numbers);
        validate(numbers);
        validateDuplication(numbers);

        this.numbers = numbers;
    }

    private void validateNullOrEmpty(List<Integer> numbers) {
        if (numbers == null) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }

        if (numbers.isEmpty()) {
            throw new LottoErrorException("잘못된 값을  입력 하셨습니다");
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoErrorException("로또 번호는 6개 입니다.");
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        HashSet<Integer> hashNumbers = new HashSet<>(numbers);
        if(numbers.size() != hashNumbers.size()){
            throw new LottoErrorException("로또 번호가 중복 됩니다..");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}

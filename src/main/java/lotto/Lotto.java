package lotto;

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
            throw new IllegalArgumentException("[ERROR] 로또가 없습니다.");
        }

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또가 없습니다.");
        }
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplication(List<Integer> numbers) {
        HashSet<Integer> hashNumbers = new HashSet<>(numbers);
        if(numbers.size() != hashNumbers.size()){
            throw new IllegalArgumentException("[ERROR] 로또번호가 중복 입니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}

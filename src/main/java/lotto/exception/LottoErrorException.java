package lotto.exception;

public class LottoErrorException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "[ERROR]";
    private static final String EMPTY = " ";

    public LottoErrorException(String message) {
        super(ERROR_MESSAGE + EMPTY + message);
    }
}

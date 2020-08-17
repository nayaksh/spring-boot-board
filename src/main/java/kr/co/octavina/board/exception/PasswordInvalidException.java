package kr.co.octavina.board.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String s) {
        super(s);
    }
}

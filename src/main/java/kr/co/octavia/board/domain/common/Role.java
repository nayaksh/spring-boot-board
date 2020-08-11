package kr.co.octavia.board.domain.common;

public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

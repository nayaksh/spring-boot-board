package kr.co.octavina.board.service.dto;

import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.common.Role;
import kr.co.octavina.board.domain.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberDto {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private Status status;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity() {
        return Member.builder()
                .id(this.id)
                .loginId(this.loginId)
                .name(this.name)
                .password(this.password)
                .role(this.role)
                .status(this.status)
                .build();
    }

    @NoArgsConstructor
    @Getter @Setter @ToString
    public static class MemberInfo {
        private Long loginId;
        private String name;
        private Status status;
    }
}

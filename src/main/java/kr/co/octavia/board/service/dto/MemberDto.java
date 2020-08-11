package kr.co.octavia.board.service.dto;

import kr.co.octavia.board.domain.Member;
import kr.co.octavia.board.domain.common.Role;
import kr.co.octavia.board.domain.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public static MemberDto toDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName())
                .status(member.getStatus())
                .role(member.getRole())
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

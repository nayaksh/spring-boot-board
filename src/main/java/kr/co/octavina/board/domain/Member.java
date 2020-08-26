package kr.co.octavina.board.domain;

import kr.co.octavina.board.domain.common.Address;
import kr.co.octavina.board.domain.common.BaseTimeEntity;
import kr.co.octavina.board.domain.common.Role;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "login_id", unique = true, length = 15)
    private String loginId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Embedded
    private Address address;

    public void updateName(String name) {
        this.name = name;
    }

    public void update(String loginId) {
        this.loginId = loginId;
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .id(this.id)
                .loginId(this.loginId)
                .password(this.password)
                .name(this.name)
                .status(this.status)
                .role(this.role)
                .createdDate(this.getCreatedDate())
                .modifiedDate(this.getModifiedDate())
                .addressDto(this.address.toDto())
                .build();
    }
}

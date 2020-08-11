package kr.co.octavia.board.service;

import kr.co.octavia.board.domain.Member;
import kr.co.octavia.board.domain.common.Role;
import kr.co.octavia.board.domain.common.Status;
import kr.co.octavia.board.repository.MemberRepository;
import kr.co.octavia.board.service.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AllArgsConstructor
class MemberServiceTest {

    private final MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 사용자_등록() {
        //given
        MemberDto newMember = new MemberDto();
        newMember.setLoginId("rarity");
        newMember.setPassword("1234");
        newMember.setName("래리티");
        newMember.setRole(Role.MEMBER);
        newMember.setStatus(Status.CREATED);
        Member memberEntity = Member.toEntity(newMember);

        //when
        Member savedMember = memberRepository.save(memberEntity);

        //then
        log.info(memberEntity.getId() + "");
        log.info(savedMember.getId() + "");
        assertThat(memberEntity.getId()).isEqualTo(savedMember.getId());
        assertThat(memberEntity.getName()).isEqualTo(savedMember.getName());
        assertThat(memberEntity.getStatus()).isEqualTo(savedMember.getStatus());
    }

    @Test
    public void 사용자_조회() {
        //given
        MemberDto oldMember = new MemberDto();
        oldMember.setLoginId("pinkamena");
        oldMember.setPassword("1234");
        oldMember.setName("핑카미나");
        oldMember.setStatus(Status.CREATED);

        //when
        Member member = memberRepository.findMemberByLoginId(oldMember.getLoginId());

        MemberDto findMember = MemberDto.toDto(member);

        //then
        assertThat(oldMember.getLoginId()).isEqualTo(findMember.getLoginId());
        assertThat(oldMember.getName()).isEqualTo(findMember.getName());
        assertThat(oldMember.getStatus()).isEqualTo(findMember.getStatus());
    }

    @Test
    public void 활성화된_사용자_조회() {
        //given
        String loginId = "rarity";

        //when
        Member member = memberRepository.findMemberByLoginIdAndStatus(loginId, Status.CREATED);
        MemberDto findMember = MemberDto.toDto(member);

        //then
        assertThat(loginId).isEqualTo(findMember.getLoginId());
        assertThat("래리티").isEqualTo(findMember.getName());
        assertThat(Role.MEMBER).isEqualTo(findMember.getRole());
        assertThat(Status.CREATED).isEqualTo(findMember.getStatus());
    }
}
package kr.co.octavina.board.service;

import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.common.Role;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.MemberRepository;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AllArgsConstructor
class MemberServiceTest {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void 사용자_등록() {
        //given
        MemberDto newMember = new MemberDto();
//        newMember.setLoginId("rarity");
//        newMember.setPassword("1234");
//        newMember.setName("래리티");
//        newMember.setLoginId("applejack");
//        newMember.setPassword("1234");
//        newMember.setName("애플잭");

        newMember.setLoginId("pinkiepie");
        newMember.setPassword("1234");
        newMember.setName("핑키파이");

        newMember.setRole(Role.MEMBER);
        newMember.setStatus(Status.CREATED);
        Member memberEntity = newMember.toEntity();

        //when
        Member savedMember = memberRepository.save(memberEntity);

        //then
        assertThat(memberEntity.getLoginId()).isEqualTo(savedMember.getLoginId());
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

        MemberDto findMember = member.toDto();

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
        MemberDto findMember = member.toDto();

        //then
        assertThat(loginId).isEqualTo(findMember.getLoginId());
        assertThat("래리티").isEqualTo(findMember.getName());
        assertThat(Role.MEMBER).isEqualTo(findMember.getRole());
        assertThat(Status.CREATED).isEqualTo(findMember.getStatus());
    }

//    @Test
//    public void 로그인_테스트() throws Exception {
//        MemberDto loginMember = MemberDto.builder().loginId("rarity").password("1234").build();
//        String loginId = memberService.login(loginMember);
//
//        assertThat(loginMember.getLoginId()).isEqualTo(loginId);
//    }
}
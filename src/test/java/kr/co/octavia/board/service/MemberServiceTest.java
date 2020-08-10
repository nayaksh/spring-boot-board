package kr.co.octavia.board.service;

import kr.co.octavia.board.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestConstructor;

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
//        Member member = new Member();
//        member.setName("김서한");
//        member.setPassword("123123123");
//        member.setLoginId("octavina");
//        member.setStatus(Status.CREATED);
//
//        //when
//        Member savedMember = memberRepository.save(member);
//
//        //then
//        log.info(member.getId() + "");
//        log.info(savedMember.getId() + "");
//        assertThat(member.getId()).isEqualTo(savedMember.getId());
    }
}
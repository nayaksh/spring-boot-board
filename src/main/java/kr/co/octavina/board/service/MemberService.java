package kr.co.octavina.board.service;

import kr.co.octavina.board.config.MemberSession;
import kr.co.octavina.board.exception.PasswordInvalidException;
import kr.co.octavina.board.exception.UserNotActivatedException;
import kr.co.octavina.board.exception.UserNotFoundException;
import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.MemberRepository;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberSession memberSession;
    private final MemberRepository memberRepository;

    // 등록
    public Long create(MemberDto memberDto) throws Exception {
        Member saveMember = memberRepository.save(memberDto.toEntity());
        return saveMember.getId();
    }

    //로그인
    public String login(MemberDto memberDto, String ip) throws Exception {
        MemberDto member = getMemberOfActivated(memberDto.getLoginId());

        if (member == null) {
            throw new UserNotFoundException("Not Exists Member : " + memberDto.getLoginId());
        }

        if (member.getStatus() == Status.DELETED) {
            throw new UserNotActivatedException("Not Exists Member : " + memberDto.getLoginId());
        }

        if (!memberDto.getPassword().equals(member.getPassword())) {
            throw new PasswordInvalidException("Invalid user password : " + memberDto.getLoginId());
        }

        // 세선에 사용자 정보 등록
        memberSession.setId(member.getId());
        memberSession.setLoginId(member.getLoginId());
        memberSession.setName(member.getName());
        memberSession.setIp(ip);
        
        return member.getLoginId();
    }

    //조회
    public MemberDto getMember(String loginId) throws Exception {
        Member findMember = memberRepository.findMemberByLoginId(loginId);
        return findMember.toDto();
    }

    //활성 회원 조회
    public MemberDto getMemberOfActivated(String loginId) throws Exception {
        Member findMember = memberRepository.findMemberByLoginIdAndStatus(loginId, Status.CREATED);
        return findMember.toDto();
    }

    public MemberDto getMemberById(Long memberId) throws Exception {
        return memberRepository.findById(memberId)
                .orElseGet(() -> Member.builder().build())
                .toDto();
    }
}

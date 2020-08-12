package kr.co.octavia.board.service;

import kr.co.octavia.board.domain.Member;
import kr.co.octavia.board.domain.common.Status;
import kr.co.octavia.board.repository.MemberRepository;
import kr.co.octavia.board.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //가입

    //로그인

    //조회
    public MemberDto getMember(String loginId) {
        Member findMember = memberRepository.findMemberByLoginId(loginId);
        return MemberDto.toDto(findMember);
    }

    //활성 회원 조회
    public MemberDto getMemberOfActivated(String loginId) {
        Member findMember = memberRepository.findMemberByLoginIdAndStatus(loginId, Status.CREATED);
        return MemberDto.toDto(findMember);
    }


}

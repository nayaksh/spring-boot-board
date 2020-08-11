package kr.co.octavia.board.repository;

import kr.co.octavia.board.domain.Member;
import kr.co.octavia.board.domain.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByLoginId(String loginId);

    Member findMemberByLoginIdAndStatus(String loginId, Status status);
}

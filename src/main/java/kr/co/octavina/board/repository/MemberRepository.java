package kr.co.octavina.board.repository;

import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByLoginId(String loginId);

    Member findMemberByLoginIdAndStatus(String loginId, Status status);
}

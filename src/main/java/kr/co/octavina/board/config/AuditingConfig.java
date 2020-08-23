package kr.co.octavina.board.config;

import kr.co.octavina.board.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AuditingConfig {

    private final MemberSession memberSession;

//    @Bean
//    AuditorAware<String> auditorAware() {
//        return new AuditorAware<String>() {
//            @Override
//            public Optional<String> getCurrentAuditor() {
//                return Optional.of(memberSession.getLoginId());
//            }
//        };
//    }

//    @Bean
//    AuditorAware<String> auditorAware() {
//        return () -> Optional.ofNullable(memberSession.getLoginId());
//    }

//    @Bean
//    AuditorAware<Long> auditorAware() {
//        return () -> Optional.ofNullable(memberSession.getId());
//    }

    @Bean
    AuditorAware<Member> auditorAware() {
        return () -> Optional.ofNullable(Member.builder().id(memberSession.getId()).build());
    }

}

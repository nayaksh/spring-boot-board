package kr.co.octavina.board.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SessionScope
@NoArgsConstructor
@Setter @Getter
@ToString
public class MemberSession implements Serializable {

    private Long id;
    private String loginId;
    private String name;
    private String ip;

}

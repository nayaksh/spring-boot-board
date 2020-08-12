package kr.co.octavia.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(basePackages = "kr.co.octavia.board.repository")
@EnableJpaAuditing(modifyOnCreate = false)
public class OctaviaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctaviaBoardApplication.class, args);
    }

}

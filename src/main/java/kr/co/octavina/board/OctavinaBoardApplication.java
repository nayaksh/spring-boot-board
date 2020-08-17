package kr.co.octavina.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ServletComponentScan
@EnableJpaRepositories(basePackages = "kr.co.octavina.board.repository")
@EnableJpaAuditing(modifyOnCreate = false)
public class OctavinaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(OctavinaBoardApplication.class, args);
    }

}
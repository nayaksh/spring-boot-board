package kr.co.octavina.board.service;

import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class FileServiceTest {

    private final FileRepository fileRepository;

    @Test
    void 파일목록_조회_등록자아이디() throws Exception {
        List<File> files = fileRepository.findFilesByCreatorId(17L);
        
        files.forEach(f -> {
            log.info("f = " + f.getName());
        });
    }
}
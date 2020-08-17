package kr.co.octavina.board.service;

import kr.co.octavina.board.config.AppResource;
import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.service.dto.FileDto;
import kr.co.octavina.board.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final AppResource appResource;

    //저장
    public void save(FileDto fileDto) {

    }

    //조회

    //수정

    //삭제

    //제거

    //목록
}

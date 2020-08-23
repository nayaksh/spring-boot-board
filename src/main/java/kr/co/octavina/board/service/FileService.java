package kr.co.octavina.board.service;

import kr.co.octavina.board.config.AppResource;
import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.repository.FileRepository;
import kr.co.octavina.board.service.dto.FileDto;
import kr.co.octavina.board.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final AppResource appResource;
    private final FileRepository fileRepository;

    // 저장
//    public void save(FileDto fileDto, Article article) {
//        File file = File.toEntity(fileDto, article);
//        fileRepository.save(file);
//    }
    public void save(FileDto fileDto, Article article) {
        File file = fileDto.toEntity(article);
        fileRepository.save(file);
    }

    public void save(List<MultipartFile> files, Article savedArticle) throws Exception {
        ArrayList<FileDto> copiedRealFiles = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue;
                }

                FileDto fileDto = copyFile(file);
                copiedRealFiles.add(fileDto);

                save(fileDto, savedArticle);
            }
        } catch (Exception e) {
            e.printStackTrace();

            for (FileDto copiedRealFile : copiedRealFiles) {
                String copiedFilePath = appResource.getStoragePath() + copiedRealFile.getPath();
                Files.deleteIfExists(Paths.get(copiedFilePath));
            }

            throw e;
        }
    }
    // 조회

    // 수정

    // 삭제

    // 제거

    // 목록

    // 로컬에 파일 복사
    public FileDto copyFile(MultipartFile file) throws IOException {
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        String storagePath = FileUtil.createPath();
        String storageFullPath = appResource.getStoragePath() + storagePath;

        if (Files.notExists(Paths.get(storageFullPath))) {
            Files.createDirectories(Paths.get(storageFullPath));
        }
        String uniqueFileName = FileUtil.createUniqueFileName();

        java.io.File destFile = new java.io.File(storageFullPath + uniqueFileName);
        file.transferTo(destFile);

        return FileDto.builder()
                .name(file.getOriginalFilename())
                .path(storagePath + uniqueFileName)
                .contentSize(file.getSize())
                .extension(ext)
                .build();
    }

    public List<FileDto> getFileByArticleId(Long articleId) {
        return fileRepository.findFilesByArticleId(articleId)
                .stream()
                .map(File::toDto)
                .collect(Collectors.toList());
    }
}

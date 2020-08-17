package kr.co.octavina.board.service;

import kr.co.octavina.board.repository.FileRepository;
import kr.co.octavina.board.config.AppResource;
import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.ArticleRepository;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final AppResource appResource;
    private final FileService fileService;
    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    //등록
    public Long createArticle(ArticleDto articleDto) throws Exception {
        Article article = Article.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }

    public Long createArticle(ArticleDto articleDto, List<MultipartFile> files) throws Exception {
        Article article = Article.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);

        for (MultipartFile file : files) {
            if (file == null || file.isEmpty()) {
                continue;
            }

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String storagePath = FileUtil.createPath();
            String storageFullPath = appResource.getStoragePath() + storagePath;

            if (Files.notExists(Paths.get(storageFullPath))) {
                Files.createDirectories(Paths.get(storageFullPath));
            }
            String uniqueFileName = FileUtil.createUniqueFileName();

            java.io.File destFile = new java.io.File(storageFullPath + uniqueFileName);
            file.transferTo(destFile);

            File saveFile = File.builder()
                    .name(file.getOriginalFilename())
                    .contentSize(file.getSize())
                    .extension(ext)
                    .path(storagePath + uniqueFileName)
                    .article(savedArticle)
                    .build();

            fileRepository.save(saveFile);
        }

        return savedArticle.getId();
    }

    //수정
    public ArticleDto modifyArticle(ArticleDto articleDto) throws Exception {
        Article updatedArticle = articleRepository.findById(articleDto.getId())
                .orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        // TODO : 파일 수정 추가
        updatedArticle.update(articleDto.getTitle(), articleDto.getContent());

        return ArticleDto.toDto(updatedArticle);
    }

    //삭제
    public void deleteArticle(ArticleDto articleDto) throws Exception {
        Article article = articleRepository.findById(articleDto.getId())
                .orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        article.update(Status.DELETED);
        //TODO: 파일 삭제

    }

    //제거
    public void eraseArticle(Long id) throws Exception {
        //TODO: 파일 제거
        articleRepository.eraseArticleById(id);
    }

    //조회
    public ArticleDto getArticle(Long articleId) throws Exception {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Not Exists Article"));
        return ArticleDto.toDto(article);
    }

    //목록 조회
    public void getArticlesByPagination() throws Exception {

    }

    //검색
    public void searchArticles() throws Exception {

    }

    //검색
    public void searchArticlesByPagination() throws Exception {

    }
}

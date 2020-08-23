package kr.co.octavina.board.service;

import kr.co.octavina.board.config.AppResource;
import kr.co.octavina.board.config.MemberSession;
import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.QArticle;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.repository.ArticleRepository;
import kr.co.octavina.board.repository.FileRepository;
import kr.co.octavina.board.repository.MemberRepository;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.ArticleDto.ArticleContentDto;
import kr.co.octavina.board.service.dto.ArticleSearchCondition;
import kr.co.octavina.board.service.dto.ArticleSearchDto;
import kr.co.octavina.board.service.dto.FileDto;
import kr.co.octavina.board.service.dto.MemberDto;
import kr.co.octavina.board.service.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final MemberSession session;
    private final AppResource appResource;
    private final MemberService memberService;
    private final FileService fileService;

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;

    // 등록
    public Long createArticle(ArticleDto articleDto) throws Exception {
        Article article = articleDto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }
    
    // 등록
    public Long createArticle(ArticleDto articleDto, List<MultipartFile> files) throws Exception {
        articleDto.setStatus(Status.CREATED);

        Member member = memberRepository.findMemberByLoginIdAndStatus(session.getLoginId(), Status.CREATED);

        Article savedArticle = articleRepository.save(articleDto.toEntity());

        fileService.save(files, savedArticle);

        return savedArticle.getId();
    }
    
    // 수정
    public ArticleDto modify(ArticleDto articleDto) throws Exception {
        Article updatedArticle = articleRepository.findById(articleDto.getId())
                .orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        // TODO : 파일 수정 추가
        updatedArticle.update(articleDto.getTitle(), articleDto.getContent());

        return updatedArticle.toDto();
    }

    // 삭제
    public void deleteArticle(ArticleDto articleDto) throws Exception {
        Article article = articleRepository.findById(articleDto.getId())
                .orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        article.update(Status.DELETED);
    }

    // 제거
    public void eraseArticle(Long articleId) throws Exception {
        //TODO: 파일 제거
        fileRepository.deleteFilesByArticleId(articleId);

        articleRepository.eraseArticleById(articleId);
    }

    // 조회
    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long articleId) throws Exception {
        ArticleDto articleDto = articleRepository.findArticlesWithCreator(articleId)
                .orElseThrow(() -> new RuntimeException("Not Exists Article"))
                .toDto();

        List<FileDto> fileDtos = fileService.getFileByArticleId(articleId);
        articleDto.setFiles(fileDtos);

        return articleDto;
    }

    // 조회
    public ArticleContentDto getArticleContent(Long articleId) throws Exception {
        return articleRepository.findArticlesWithCreator(articleId)
                .map(ArticleContentDto::new)
                .orElse(null);
//                .orElseGet(ArticleContentDto::new);
//                .orElseThrow(() -> new RuntimeException("Not Exists Article"));

    }

    // 목록 조회
    public Page<Article> getArticlesByPagination(PageRequestDto pageRequestDto) throws Exception {
        PageRequest pageRequest = pageRequestDto.of();
        return articleRepository.findAll(pageRequest);
    }

    //검색
    public void searchArticles() throws Exception {

    }

    //검색
    public Page<ArticleSearchDto> searchArticlesByPagination(ArticleSearchCondition condition, Pageable pageable) throws Exception {
        return articleRepository.findSearchArticlesByPagination(condition, pageable);
    }
}

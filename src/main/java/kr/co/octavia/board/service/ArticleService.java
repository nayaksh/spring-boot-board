package kr.co.octavia.board.service;

import kr.co.octavia.board.domain.Article;
import kr.co.octavia.board.repository.ArticleRepository;
import kr.co.octavia.board.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;

    //등록
    public Long createArticle(ArticleDto articleDto) throws Exception {
        Article article = Article.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }

    //수정
    public ArticleDto modifyArticle(ArticleDto articleDto) throws Exception {
//        Optional<Article> article = articleRepository.findById(articleDto.getId());
//        Article updatedArticle = article.orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        Article updatedArticle = articleRepository.findById(articleDto.getId())
                .orElseThrow(() -> new Exception("Not Exists Article :" + articleDto.getId()));

        // TODO : 파일 수정 추가
        updatedArticle.update(articleDto.getTitle(), articleDto.getContent());

        return ArticleDto.toDto(updatedArticle);
    }

    //삭제
    public void deleteArticle() throws Exception {

    }

    //제거
    public void removeArticle() throws Exception {

    }

    //조회
    public ArticleDto getArticle(Long articleId) throws Exception {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new Exception("Not Exists Article"));
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

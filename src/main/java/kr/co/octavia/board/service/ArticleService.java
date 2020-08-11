package kr.co.octavia.board.service;

import kr.co.octavia.board.domain.Article;
import kr.co.octavia.board.repository.ArticleRepository;
import kr.co.octavia.board.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    //등록
    public Long createArticle(ArticleDto articleDto) throws Exception {
        Article article = Article.toEntity(articleDto);
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }

    //수정
    public void modifyArticle() throws Exception {

    }

    //삭제
    public void deleteArticle() throws Exception {

    }

    //제거
    public void removeArticle() throws Exception {

    }

    //조회
    public void getArticle() throws Exception {

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

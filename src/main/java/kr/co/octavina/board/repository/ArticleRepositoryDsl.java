package kr.co.octavina.board.repository;

import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.ArticleSearchCondition;
import kr.co.octavina.board.service.dto.ArticleSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepositoryDsl {

    List<ArticleDto> getArticlesByPagination(ArticleSearchCondition condition);

    List<ArticleSearchDto> getSearchArticles(ArticleSearchCondition condition);

    Page<ArticleSearchDto> findArticlesByPagination(ArticleSearchCondition condition, Pageable pageable);

    Page<ArticleSearchDto> findArticlesWithCreatorByPagination(ArticleSearchCondition condition, Pageable pageable);
}

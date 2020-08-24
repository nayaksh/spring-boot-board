package kr.co.octavina.board.repository.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparablePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.QArticle;
import kr.co.octavina.board.domain.QMember;
import kr.co.octavina.board.repository.ArticleRepositoryDsl;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.ArticleSearchCondition;
import kr.co.octavina.board.service.dto.ArticleSearchDto;
import kr.co.octavina.board.service.dto.QArticleSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static kr.co.octavina.board.domain.QArticle.article;
import static kr.co.octavina.board.domain.QMember.member;

public class ArticleRepositoryDslImpl implements ArticleRepositoryDsl {

    private final JPAQueryFactory queryFactory;

    public ArticleRepositoryDslImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ArticleDto> getArticlesByPagination(ArticleSearchCondition condition) {
        return null;
    }

    @Override
    public List<ArticleSearchDto> getSearchArticles(ArticleSearchCondition condition) {
        return queryFactory
                .select(new QArticleSearchDto(
                        article.title,
                        article.content,
                        article.creator.loginId.as("creatorLoginId"),
                        article.creator.name.as("creatorName")))
                .from(article)
                .innerJoin(article.creator, member)
                .where(titleLike(condition.getTitle()))
//                .limit(1L)
                .fetch();
    }

    @Override
    public Page<ArticleSearchDto> findSearchArticlesByPagination(ArticleSearchCondition condition, Pageable pageable) {
        OrderSpecifier<?> sortedColumn = getSortedColumn(Order.DESC, article,"createdDate");
        OrderSpecifier<?> sortedColumn2 = getSortedColumn(Order.ASC, article,"id");

        List<OrderSpecifier<?>> order = new ArrayList<>();
        order.add(sortedColumn);
        order.add(sortedColumn2);


        QueryResults<ArticleSearchDto> result = queryFactory
                .select(new QArticleSearchDto(
                        article.title,
                        article.content,
                        article.creator.loginId.as("creatorLoginId"),
                        article.creator.name.as("creatorName")))
                .from(article)
                .innerJoin(article.creator)
                .where(titleLike(condition.getTitle()))
//                .orderBy(new OrderSpecifier<>(Order.DESC, article.createdDate))
                .orderBy(order.toArray(new OrderSpecifier[0]))
                .orderBy()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private BooleanExpression titleLike(String title) {
        return StringUtils.isEmpty(title) ? null : article.title.contains(title);
    }

    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }
}

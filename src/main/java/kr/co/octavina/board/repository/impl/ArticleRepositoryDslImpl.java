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
import org.springframework.data.domain.Sort;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

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
    public Page<ArticleSearchDto> findArticlesByPagination(ArticleSearchCondition condition, Pageable pageable) {
        QueryResults<ArticleSearchDto> result = queryFactory
                .select(new QArticleSearchDto(
                        article.title,
                        article.content,
                        article.creator.loginId.as("creatorLoginId"),
                        article.creator.name.as("creatorName")))
                .from(article)
                .innerJoin(article.creator)
                .where(titleLike(condition.getTitle()), creatorNameEq(condition.getCreatorName()))
                .orderBy(getOrderBySort(pageable, article))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public Page<ArticleSearchDto> findArticlesWithCreatorByPagination(ArticleSearchCondition condition, Pageable pageable) {
        pageable.getSort();

        QueryResults<ArticleSearchDto> results = queryFactory
                .select(new QArticleSearchDto(
                        article.title,
                        article.content,
                        article.creator.loginId.as("creatorLoginId"),
                        article.creator.name.as("creatorName")))
                .from(article)
                .innerJoin(article.creator)
                .where(creatorNameEq("래리티"))
//                .orderBy(getSortedColumn(Sort.Direction.DESC, article, "createdDate"),
//                        getSortedColumn(Sort.Direction.ASC, article.creator, "name"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    private BooleanExpression titleLike(String title) {
        return StringUtils.isEmpty(title) ? null : article.title.contains(title);
    }

    private BooleanExpression creatorNameEq(String creator) {
        return StringUtils.isEmpty(creator) ? null : article.creator.name.eq(creator);
    }


    public static OrderSpecifier<?>[] getOrderBySort(Pageable pageable, Path<?> parent) {
        return pageable.getSort().stream()
                .map(s -> getSortedColumn(s.getDirection(), parent, s.getProperty()))
                .toArray(OrderSpecifier[]::new);
                //.toArray(sort -> new OrderSpecifier[0]);
    }

    public static OrderSpecifier<?> getSortedColumn(Sort.Direction order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(Order.valueOf(order.name()), fieldPath);
    }

    public static OrderSpecifier<?> getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }
}

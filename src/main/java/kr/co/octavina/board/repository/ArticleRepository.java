package kr.co.octavina.board.repository;

import kr.co.octavina.board.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositoryDsl {

//    @Query("select a from Article a inner join File f on a.id = f.id where a.id = :id")
//    @Query("select a from Article a inner join Member f on a.id = f.id where a.id = :id")
//    Article findAllWithFilesById(@Param("id") Long id);

    @Modifying
    @Query("delete from Article a where a.id = :id")
    void eraseArticleById(@Param("id") Long id);

    @Query("select a from Article a join fetch a.creator where a.id = :id")
//    @Query("select a from Article a join fetch a.creator left join fetch a.modifier where a.id = :id")
    Optional<Article> findArticlesWithCreator(@Param("id") Long id);

    @Query(value = "select a from Article a inner join fetch a.creator",
            countQuery = "select count(a) from Article a")
    Page<Article> findAllByPagination(@Param("pageable") Pageable pageable);
}

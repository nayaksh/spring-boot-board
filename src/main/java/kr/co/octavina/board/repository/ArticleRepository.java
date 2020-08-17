package kr.co.octavina.board.repository;

import kr.co.octavina.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

//    @Query("select a from Article a left join File f on a.id = f.id where a.id = :id")
//    Article findArticleById(@Param("id") Long id);


    @Modifying
    @Query("delete from Article m where m.id = :id")
    void eraseArticleById(@Param("id") Long id);
}

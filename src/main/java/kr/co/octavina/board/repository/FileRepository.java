package kr.co.octavina.board.repository;

import kr.co.octavina.board.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query("select f from File f where f.article.id = :articleId")
    List<File> findFilesByArticleId(@Param("articleId") Long articleId);

    @Modifying
    @Query("delete from File f where f.article.id = :articleId")
    void deleteFilesByArticleId(@Param("articleId") Long articleId);

    @Query("select f from File f where f.creator.id = :creatorId order by f.name")
    List<File> findFilesByCreatorId(@Param("creatorId") Long creatorId);
}

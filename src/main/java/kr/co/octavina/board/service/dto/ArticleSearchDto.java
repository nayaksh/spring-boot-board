package kr.co.octavina.board.service.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ArticleSearchDto {

    private String title;
    private String content;
    private String creatorLoginId;
    private String creatorName;

    @QueryProjection
    public ArticleSearchDto(String title, String content, String creatorLoginId, String creatorName) {
        this.title = title;
        this.content = content;
        this.creatorLoginId = creatorLoginId;
        this.creatorName = creatorName;
    }
}

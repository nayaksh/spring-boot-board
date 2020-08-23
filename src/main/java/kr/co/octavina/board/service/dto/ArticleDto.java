package kr.co.octavina.board.service.dto;

import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.File;
import kr.co.octavina.board.domain.Member;
import kr.co.octavina.board.domain.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private MemberDto creator;
    private LocalDateTime createdDate;
    private MemberDto modifier;
    private LocalDateTime modifiedDate;
    private List<FileDto> files = new ArrayList<>();
    private Status status;

    public Article toEntity() throws Exception {
        return Article.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .status(this.status)
                .build();
    }

    @Data
    public static class ArticleContentDto {
        private String title;
        private String content;

        public ArticleContentDto(Article article) {
            this.title = article.getTitle();
            this.content = article.getContent();
        }
    }
}

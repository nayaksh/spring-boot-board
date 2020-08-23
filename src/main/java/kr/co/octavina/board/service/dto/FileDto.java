package kr.co.octavina.board.service.dto;

import com.sun.xml.bind.v2.schemagen.xmlschema.Particle;
import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class FileDto {

    private Long id;
    private String name;
    private String path;
    private long contentSize;
    private String extension;
    private ArticleDto articleDto;
    private MemberDto creator;
    private LocalDateTime createdDate;
    private MemberDto modifier;
    private LocalDateTime modifiedDate;


    public File toEntity(Article article) {
        return File.builder()
                .id(this.id)
                .contentSize(this.contentSize)
                .extension(this.extension)
                .name(this.name)
                .path(this.path)
                .article(article)
                .build();
    }
}

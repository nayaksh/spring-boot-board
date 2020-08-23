package kr.co.octavina.board.domain;

import kr.co.octavina.board.domain.common.BaseEntity;
import kr.co.octavina.board.service.dto.FileDto;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "file")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String path;

    private long contentSize;
    private String extension;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "article_id")
    private Article article;

    public FileDto toDto() {
        MemberDto creatorDto = MemberDto.builder().build();
        MemberDto modifierDto = MemberDto.builder().build();

        if (this.getCreator() != null) {
            creatorDto = this.getCreator().toDto();
        }

        if (this.getModifier() != null) {
            modifierDto = this.getModifier().toDto();
        }

        return FileDto.builder()
                .id(this.id)
                .name(this.name)
                .extension(this.extension)
                .contentSize(this.contentSize)
                .path(this.path)
                .creator(creatorDto)
                .createdDate(this.getCreatedDate())
                .modifier(modifierDto)
                .modifiedDate(this.getModifiedDate())
                .build();
    }

    public FileDto toDto(Article article) {
        return FileDto.builder()
                .id(this.id)
                .name(this.name)
                .extension(this.extension)
                .contentSize(this.contentSize)
                .path(this.path)
                .articleDto(article.toDto())
                .build();
    }
}

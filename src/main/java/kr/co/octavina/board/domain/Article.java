package kr.co.octavina.board.domain;

import kr.co.octavina.board.domain.common.BaseEntity;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.service.dto.ArticleDto;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "article_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.CREATED;

    public ArticleDto toDto() {
        MemberDto creatorDto = MemberDto.builder().build();
        MemberDto modifierDto = MemberDto.builder().build();

        if (this.getCreator() != null) {
            creatorDto = this.getCreator().toDto();
        }

        if (this.getModifier() != null) {
            modifierDto = this.getModifier().toDto();
        }

        return ArticleDto.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .creator(creatorDto)
                .createdDate(this.getCreatedDate())
                .modifier(modifierDto)
                .modifiedDate(this.getModifiedDate())
                .status(this.status)
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(Status status) {
        this.status = status;
    }

}

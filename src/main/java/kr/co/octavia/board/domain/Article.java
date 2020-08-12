package kr.co.octavia.board.domain;

import kr.co.octavia.board.domain.common.BaseEntity;
import kr.co.octavia.board.domain.common.Status;
import kr.co.octavia.board.service.dto.ArticleDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private List<File> files = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public static Article toEntity(ArticleDto articleDto) {
        Member member = null;
        List<File> files = new ArrayList<>();

        if (articleDto.getMemberDto() != null) {
            member = Member.toEntity(articleDto.getMemberDto());
        }

        if (articleDto.getFiles() != null) {
            articleDto.getFiles().forEach(fileDto -> {
                files.add(File.toEntity(fileDto));
            });
        }

        return Article.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .files(files)
                .member(member)
                .status(articleDto.getStatus())
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content, ArrayList<File> files) {
        this.title = title;
        this.content = content;
        this.files = files;
    }

    public void update(Status status) {
        this.status = status;
    }
}

package kr.co.octavia.board.service.dto;

import kr.co.octavia.board.domain.Article;
import kr.co.octavia.board.domain.File;
import kr.co.octavia.board.domain.Member;
import kr.co.octavia.board.domain.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class ArticleDto {

    private Long id;
    private String title;
    private String content;
    private MemberDto memberDto;
    private List<File> files = new ArrayList<>();
    private Status status;

    public static ArticleDto toDto(Article article) {
        MemberDto memberDto = null;

        if (article.getMember() != null) {
            memberDto = MemberDto.toDto(article.getMember());
        }

        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .files(article.getFiles())
                .memberDto(memberDto)
                .status(article.getStatus())
                .build();
    }
}

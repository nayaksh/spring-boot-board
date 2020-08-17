package kr.co.octavina.board.service.dto;

import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.common.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private MemberDto memberDto;
    private List<FileDto> files = new ArrayList<>();
    private Status status;

    public static ArticleDto toDto(Article article) {
        MemberDto memberDto = null;
        List<FileDto> filesDto = new ArrayList<>();

        if (article.getMember() != null) {
            memberDto = MemberDto.toDto(article.getMember());
        }

//        if (article.getFiles() != null) {
//            List<FileDto> files = new ArrayList<>();
//
//            article.getFiles().forEach(file -> {
//                filesDto.add(FileDto.toDto(file));
//            });
//        }

        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .files(filesDto)
                .memberDto(memberDto)
                .status(article.getStatus())
                .build();
    }
}

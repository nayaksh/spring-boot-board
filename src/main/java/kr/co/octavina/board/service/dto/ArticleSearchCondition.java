package kr.co.octavina.board.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ArticleSearchCondition {
    private String title;
    private String creatorName;
    private String modifierName;
    private LocalDate startDate;
    private LocalDate endDate;
}

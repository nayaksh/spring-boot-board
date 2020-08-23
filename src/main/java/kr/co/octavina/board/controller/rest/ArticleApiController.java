package kr.co.octavina.board.controller.rest;

import kr.co.octavina.board.repository.ArticleRepository;
import kr.co.octavina.board.service.ArticleService;
import kr.co.octavina.board.service.dto.ArticleDto.ArticleContentDto;
import kr.co.octavina.board.service.dto.PageRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @GetMapping("/api/article/{id}")
    public Map<String, Object> getArticle(@PathVariable("id") Long articleId) throws Exception {
        Map<String, Object> response = new HashMap<>();

        ArticleContentDto articleContent = articleService.getArticleContent(articleId);

        if (articleContent == null) {
            response.put("msg", HttpStatus.NOT_FOUND);
        } else {
            response.put("msg", HttpStatus.OK);
        }

        response.put("data", articleContent);

        return response;
    }

    @RequestMapping("/api/article/list")
    public void getArticlesPagination(@RequestBody PageRequestDto pageRequestDto) {
//        PageRequestDto pageRequestDto = new PageRequestDto();
//        pageRequestDto.setPage(1);
//        pageRequestDto.setSize(20);
//        pageRequestDto.setDirection(Sort.Direction.DESC);

        log.info(pageRequestDto.toString());

        articleRepository.findAll(pageRequestDto.of());
    }
}

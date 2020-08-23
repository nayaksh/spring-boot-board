package kr.co.octavina.board.controller.web;

import kr.co.octavina.board.service.ArticleService;
import kr.co.octavina.board.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    // 등록 화면
    @RequestMapping(value = "/article/create/form", method = RequestMethod.GET)
    public String form(Model model) {
        ArticleDto articleDto = new ArticleDto();
        model.addAttribute("articleDto", articleDto);
        return "/article/write";
    }

    // 등록
    @RequestMapping(value = "/article/create", method = RequestMethod.POST)
    public String create(ArticleDto articleDto, @RequestParam("file") List<MultipartFile> files) throws Exception {
        Long id = articleService.createArticle(articleDto, files);

        return "redirect:/article/list";
    }

    // 조회
    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    public String getArticle(@PathVariable("id") Long articleId, Model model) throws Exception {
        ArticleDto article = articleService.getArticle(articleId);

        model.addAttribute("article",article);

        return "/article/view";

    }

    // 수정 화면
    @RequestMapping(value = "/article/update/form/{articleId}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("articleId") Long articleId, Model model) throws Exception {
        ArticleDto articleDto = articleService.getArticle(articleId);
        model.addAttribute("articleDto", articleDto);
        return "article/modify";
    }
    
    // 수정
    @RequestMapping(value = "/article/update", method = RequestMethod.POST)
    public String update(ArticleDto articleDto, @RequestParam("file") List<MultipartFile> files) throws Exception {
        ArticleDto modify = articleService.modify(articleDto);
        return "redirect:/article/list";
    }

    // 제거
    @RequestMapping(value = "/article/erase/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long articleId) throws Exception {
        articleService.eraseArticle(articleId);
        return "redirect:/article/list";
    }

    // 목록
    @RequestMapping(value = "/article/list", method = RequestMethod.GET)
    public String articles(Model model) throws Exception {
        log.info("글목록 페이지.....");
        return "/article/list";
    }

    //검색

}

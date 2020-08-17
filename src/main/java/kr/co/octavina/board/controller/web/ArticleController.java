package kr.co.octavina.board.controller.web;

import kr.co.octavina.board.domain.Article;
import kr.co.octavina.board.domain.common.Status;
import kr.co.octavina.board.service.ArticleService;
import kr.co.octavina.board.service.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {


    private final ArticleService articleService;
    //등록
    //수정
    //삭제
    //리스트
    //검색

    @RequestMapping(value = "/create/form", method = RequestMethod.GET)
    public String form(Model model) {
        ArticleDto articleDto = new ArticleDto();
        model.addAttribute("articleDto", articleDto);
        return "/article/write";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(ArticleDto articleDto, @RequestParam("file") List<MultipartFile> files) throws Exception {
        log.info(articleDto.toString());

//        articleService.createArticle(articleDto);
        articleDto.setStatus(Status.CREATED);
        Long id = articleService.createArticle(articleDto, files);
        log.info("글등록 : " + id);
//        for (MultipartFile file : files) {
//            log.info(file.getName());
//            log.info(file.getOriginalFilename());
//            log.info(file.getContentType());
//            log.info(file.getSize()+"");
//        }

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ArticleDto articleDto, @RequestParam("file") List<MultipartFile> files) throws Exception {

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String articles(Model model) throws Exception {
        log.info("글목록 페이지.....");
        return "/article/list";
    }

}

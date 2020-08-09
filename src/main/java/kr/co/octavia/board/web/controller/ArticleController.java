package kr.co.octavia.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("/list")
    public String articles(Model model) throws Exception {
        log.info("글목록 페이지.....");
        return "/article/list";
    }
}

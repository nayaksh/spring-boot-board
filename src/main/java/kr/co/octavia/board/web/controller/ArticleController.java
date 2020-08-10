package kr.co.octavia.board.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {


    //등록
    //수정
    //삭제
    //리스트
    //검색

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String articles(Model model) throws Exception {
        log.info("글목록 페이지.....");
        return "/article/list";
    }


}

package kr.co.octavia.board.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class HomeController {

    //@RequestMapping(value = {"", "/index"})
    @RequestMapping("/")
    public String home(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("name","옥타비아");
        request.setAttribute("tempVal","테스트 ㅋㅋㅋㅋㅋ");
        return "index";
    }

    @RequestMapping("/inspection")
    public String serviceInspection() throws Exception {
        return "inspection";
    }
}

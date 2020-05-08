package kr.co.octavia.board.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    //@RequestMapping(value = {"", "/index"})
    @RequestMapping("")
    public String home(HttpServletRequest request, Model model) throws Exception {
        model.addAttribute("name","옥타비아");
        request.setAttribute("tempVal","테스트value111");
        return "index";
    }
}

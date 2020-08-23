package kr.co.octavina.board.controller.web;

import kr.co.octavina.board.service.MemberService;
import kr.co.octavina.board.service.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    // 가입


    // 로그인 // security
    @RequestMapping("/member/login/page")
    public String loginPage() throws Exception {
        return "member/login";
    }

    @RequestMapping("/member/login")
    public String login(MemberDto memberDto, HttpServletRequest request, Model model) throws Exception {
        String loginId = memberService.login(memberDto, request.getRemoteAddr());
        model.addAttribute("loginId", loginId);
        return "index";
    }
}

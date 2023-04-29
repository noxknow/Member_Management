package com.codingrecipe.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") // 기본 페이지 요청 메서드
    public String index() {
        return "index"; // => index.html 파일을 불러온다.
    }
}

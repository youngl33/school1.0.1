package com.school.controller;

import com.school.dtoObject.BbsArticle;
import com.school.service.BbsArticleService;
import com.school.service.BbsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/bbsarticle")
@Controller
public class BbsArticleController {

    @Autowired
    private BbsCommentService bbsCommentService;

    @Autowired
    private BbsArticleService bbsArticleService;

    @GetMapping("/index")
    public String index(@RequestParam(value = "page",defaultValue = "0") Integer page,
                        Model model){
        PageRequest request = PageRequest.of(page, 9);
        Page<BbsArticle> bbsArticlePage = bbsArticleService.findAll(request);
        model.addAttribute("bbsArticles", bbsArticlePage);
        return "/bbsarticle/index";
    }
}

package com.kstory.webservice2.springboot.web;

import com.kstory.webservice2.springboot.config.auth.LoginUser;
import com.kstory.webservice2.springboot.config.auth.dto.SessionUser;
import com.kstory.webservice2.springboot.domain.user.User;
import com.kstory.webservice2.springboot.service.posts.PostsService;
import com.kstory.webservice2.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

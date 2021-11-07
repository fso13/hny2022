package ru.drudenko.hny2022.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.drudenko.hny2022.dto.A;
import ru.drudenko.hny2022.dto.Q;
import ru.drudenko.hny2022.service.QService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/q")
public class QController {
    private final QService service;

    @GetMapping("/start")
    public String start(Model model) {
        Q q = service.start();
        model.addAttribute("q", q);
        model.addAttribute("answer", A.builder().qId(q.getId()).build());

        return "q";
    }

    @PostMapping
    public String postAnswer(@ModelAttribute("answer") A answer, Model model) {
        Q q = service.postAnswer(answer);
        model.addAttribute("q", q);
        model.addAttribute("answer", A.builder().qId(q.getId()).build());

        return "q";
    }
}

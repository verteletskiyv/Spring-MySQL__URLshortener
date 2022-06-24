package com.study.urlshortener_spring.controllers;

import com.study.urlshortener_spring.models.Links;
import com.study.urlshortener_spring.repos.LinksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private LinksRepository linksRepository;

    @GetMapping("/")
    public String main(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {
        Iterable<Links> links = linksRepository.findAll();
        model.addAttribute("links", links);
        if (error.equals("url_short")){
            model.addAttribute("error", "Добавить такое сокращение невозвожно, используйте другое!");
        }
        if (error.equals("url_full")){
            model.addAttribute("error", "Поле не должно быть пустым!");
        }
        return "index";
    }


    @PostMapping("/add")
    public String store(
            @RequestParam String url_full,
            @RequestParam String url_short) {

        if (url_full.isEmpty() || url_short.isEmpty())
            return "redirect:/?error=url_full";

        if (isExists(url_short))
            return "redirect:/?error=url_short";
        url_full = checkInputLong(url_full);

        Links link = new Links(url_full, url_short);
        linksRepository.save(link);

        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    public boolean isExists(String s) {
        boolean x = false;
        Iterable<Links> links = linksRepository.findAll();
        for (Links i : links) {
            if (i.getUrl_short().equals(s)) {
                x = true;
                break;
            }
        }
        return x;
    }

    public String checkInputLong(String l) {
        StringBuilder oneForLong = new StringBuilder(l.replaceAll(" ", ""));
        if (!l.contains("http"))
            l = oneForLong.insert(0, "https://").toString();
        return l;
    }

}
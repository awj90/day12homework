package sg.edu.nus.iss.app.day12homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import sg.edu.nus.iss.app.day12homework.Corpus;
import sg.edu.nus.iss.app.day12homework.service.CorpusService;

@Controller
public class CorpusController {
    
    @Autowired
    CorpusService corpusService;

    @GetMapping("/")
    public String renderHomePage(Model model) {
        model.addAttribute("corpus", new Corpus());
        return "index";
    }

    @PostMapping("/analyze")
    public String displayResults(Model model, @ModelAttribute Corpus corpus) {
        model.addAttribute("listOfWords", corpusService.analyze(corpus.getContent()));
        return "result";
    }
}

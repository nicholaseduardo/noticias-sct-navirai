/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.noticias.manter_noticias;

import br.edu.ifms.noticias.manter_comentario.Comentario;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author santos
 */
@Controller
@RequestMapping("noticia")
public class NoticiaController {

    @Autowired
    private NoticiaRepository repo;

    @GetMapping("/new")
    public String showAddForm(Noticia noticia) {
        return "add-noticia";
    }

    @PostMapping("/add")
    @Transactional
    public String add(
            @Valid Noticia noticia,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "add-noticia";
        }
        repo.save(noticia);
        return "redirect:/noticia";
    }

    @GetMapping
    public String showList(Model model) {
        model.addAttribute("noticias", repo.findAll());
        return "index";
    }

    @GetMapping("/view/{id}")
    public String view(
            @PathVariable("id") Long id, Model model) {
        Noticia noticia = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido"));
        model.addAttribute("noticia", noticia);
        model.addAttribute("comentario", new Comentario());
        return "view-noticia";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(
            @PathVariable("id") Long id, Model model) {
        Noticia noticia = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido"));
        model.addAttribute("noticia", noticia);
        return "update-noticia";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @Valid Noticia noticia,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            noticia.setId(id);
            return "update-noticia";
        }
        repo.save(noticia);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            Model model) {
        Noticia noticia = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido!"));
        repo.delete(noticia);
        return "redirect:/index";
    }
}

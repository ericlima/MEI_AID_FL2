package pt.ipcb.ad.aid_pl2.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pt.ipcb.ad.aid_pl2.model.Utilizador;
import pt.ipcb.ad.aid_pl2.service.UtilizadorRepository;


@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UtilizadorController {

    private UtilizadorRepository utilizadorRepository;

    public UtilizadorController(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/listar";
    }

    @GetMapping("/novo")
    public String novo(Model utilizador) {
        return "index";
    }

    @PostMapping("/registar")
    public ModelAndView registar(@ModelAttribute Utilizador utilizador, HttpServletResponse httpServletResponse) {
        if (utilizador == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        utilizadorRepository.save(utilizador);
        ModelAndView modelAndView = new ModelAndView("dados-utilizador"); // Nome do arquivo HTML
        modelAndView.addObject("utilizador", utilizador);
        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar() {
        Iterable<Utilizador> utilizadores = utilizadorRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("lista-utilizadores"); // Nome do arquivo HTML
        modelAndView.addObject("utilizadores", utilizadores);
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerUtilizador(@PathVariable("id") Long id) {
        utilizadorRepository.deleteById(id);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarUtilizador(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update-utilizador");
        Utilizador utilizador = utilizadorRepository.findById(id).orElse(null);

        if (utilizador != null) {
            modelAndView.addObject("utilizador", utilizador);
        } else {
            modelAndView.addObject("erro", "Utilizador não encontrado!");
        }

        return modelAndView;
    }

}

package pt.ipcb.ad.aid_pl2.controller;

import org.springframework.web.bind.annotation.*;
import pt.ipcb.ad.aid_pl2.model.Utilizador;
import pt.ipcb.ad.aid_pl2.service.UtilizadorRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UtilizadorRestController {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorRestController(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    @GetMapping("/listar")
    @ResponseBody
    public Iterable<Utilizador> listar_json() {
        return utilizadorRepository.findAll();
    }

    @PostMapping("/atualizar")
    public boolean atualizar(Utilizador utilizador) {
        if (utilizador != null && utilizadorRepository.existsById(utilizador.getId())) {
            utilizadorRepository.save(utilizador);
            return true;
        }
        return false;
    }

    @GetMapping("/excluir/{id}")
    public boolean excluir(@PathVariable("id") Long id) {
        if (utilizadorRepository.existsById(id)) {
            utilizadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

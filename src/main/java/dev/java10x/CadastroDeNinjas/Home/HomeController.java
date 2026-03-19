package dev.java10x.CadastroDeNinjas.Home;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoService;
import dev.java10x.CadastroDeNinjas.Ninjas.NinjaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final NinjaService ninjaService;
    private final MissaoService missaoService;

    public HomeController(NinjaService ninjaService, MissaoService missaoService) {
        this.ninjaService = ninjaService;
        this.missaoService = missaoService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("totalNinjas", ninjaService.listarNinjas().size());
        model.addAttribute("totalMisoes", missaoService.listarMissoes().size());

        return "index";
    }

}

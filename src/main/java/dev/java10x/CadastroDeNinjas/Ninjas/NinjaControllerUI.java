package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.Dificuldade;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    private final MissaoService missaoService;

    public NinjaControllerUI(NinjaService ninjaService, MissaoService missaoService) {
        this.ninjaService = ninjaService;
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        model.addAttribute("ninjas", ninjaService.listarNinjas());
        return "listarNinjas";
    }

    @GetMapping("/{id}")
    public String detalhesNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id).orElse(null);
        model.addAttribute("ninja", ninja);
        return "detalhesNinja";
    }

    @GetMapping("/novo")
    public String novoNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", missaoService.listarMissoes());
        model.addAttribute("ranks", Rank.values());
        return "formNinja";
    }

    @PostMapping("/criar")
    public String criarNinja(@ModelAttribute("ninja") NinjaDTO ninjaDTO) {
        ninjaService.criarNinja(ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarNinja(@PathVariable Long id, Model model) {
        model.addAttribute("ninja", ninjaService.listarNinjasPorId(id).orElse(null));
        //List<MissaoDTO> listaDeMissoes = missaoService.listarMissoes();
        model.addAttribute("missoes", missaoService.listarMissoes());
        model.addAttribute("ranks",Rank.values());
        return "formNinja";
    }

    @PutMapping("/editar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute NinjaDTO ninjaDTO) {
        ninjaService.atualizarNinja(id, ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }

    @ModelAttribute("dificuldades")
    public Dificuldade[] popularDificuldades() {
        return Dificuldade.values();
    }

    @ModelAttribute("ranks")
    public Rank[] getRanks(){
        return Rank.values();
    }
}

package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoDTO;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoModel;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;
    @Autowired
    private MissaoService missaoService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        model.addAttribute("ninjas", ninjaService.listarNinjas());
        return "listarNinjas";
    }

    @GetMapping("/{id}")
    public String detalhesNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);
        model.addAttribute("ninja", ninja);
        return "detalhesNinja";
    }

    @GetMapping("/novo")
    public String novoNinja(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", ninjaService.listarMissoes());
        model.addAttribute("ranks", ninjaService.listarRanks());
        return "formNinja";
    }

    @PostMapping("/criar")
    public String criarNinja(NinjaDTO ninjaDTO) {
        ninjaService.criarNinja(ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarNinja(@PathVariable Long id, Model model) {
        model.addAttribute("ninja", ninjaService.listarNinjasPorId(id));
        List<MissaoDTO> listaDeMissoes = missaoService.listarMissoes();
        model.addAttribute("missoes", listaDeMissoes);
        model.addAttribute("ranks", RankNinja.values());
        return "formNinja";
    }

    @PostMapping("/editar/{id}")
    public String atualizarNinja(@PathVariable Long id, NinjaDTO ninjaDTO) {
        ninjaService.atualizarNinja(id, ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }
}

package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/missoes/ui")
public class MissaoControllerUI {

    private final MissaoService missaoService;

    public MissaoControllerUI(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        model.addAttribute("missoes", missaoService.listarMissoes());
        return "listarMissoes";
    }

    @GetMapping("/novo")
    public String novaMissao(Model model){
        model.addAttribute("missao", new MissaoDTO());
        return "formMissao";
    }

    @PostMapping("/criar")
    public String criarMissao(@ModelAttribute("missao") MissaoDTO missaoDTO){
        missaoService.criarMissao(missaoDTO);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarMissao(@PathVariable Long id, Model model){
        MissaoDTO missaoDTO = missaoService.listarMissoesPorId(id).orElse(null);
        model.addAttribute("missao", missaoDTO);
        return "formMissao";
    }

    @PostMapping("/atualizar/{id}")
    public String AtualizarMissao(@PathVariable Long id, @ModelAttribute("missao") MissaoDTO missaoDTO){
        missaoService.atualizarMissao(id, missaoDTO);
        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id){
        missaoService.deletarMissao(id);
        return "redirect:/missoes/ui/listar";
    }


    @ModelAttribute("dificuldades")
    public Dificuldade[] popularDificuldades() {
        return Dificuldade.values();
    }
}


package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService, MissaoMapper missaoMapper) {
        this.missaoService = missaoService;
    }

    // Criar Missao
    @PostMapping("/criar")
    public MissaoDTO criarMissao(@RequestBody MissaoDTO missoes) {
        return missaoService.criarMissao(missoes);
    }

    // Listar Missoes
    @GetMapping("/listar")
    public List<MissaoModel> listarMissoes(){
        return missaoService.listarMissoes();
    }

    // Listar Missoes por ID
    @GetMapping("/listar/{id}")
    public MissaoModel listarMissoes(@PathVariable Long id) {
        return missaoService.listarMissoesPorId(id);
    }

    // Deletar Missao
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id) {
        missaoService.deletarMissao(id);
    }

    // Atualizar Missao
    @PutMapping("/atualizar/{id}")
    public MissaoModel alterarMissao(@PathVariable Long id, @RequestBody MissaoModel missaoAtualizada) {
        return missaoService.atualizarMissao(id, missaoAtualizada);
    }
}

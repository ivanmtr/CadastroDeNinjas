package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // Criar Missao
    @PostMapping("/criar")
    public MissoesModel criarMissao(@RequestBody MissoesModel missoes) {
        return missoesService.criarMissao(missoes);
    }

    // Listar Missoes
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes(){
        return missoesService.listarMissoes();
    }

    // Listar Missoes por ID
    @GetMapping("/listar/{id}")
    public MissoesModel listarMissoes(@PathVariable Long id) {
        return missoesService.listarMissoesPorId(id);
    }

    // Deletar Missao
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable Long id) {
        missoesService.deletarMissao(id);
    }

    // Atualizar Missao
    @PutMapping("/atualizar/{id}")
    public MissoesModel alterarMissao(@PathVariable Long id, @RequestBody MissoesModel missaoAtualizada) {
        return missoesService.atualizarMissao(id, missaoAtualizada);
    }
}

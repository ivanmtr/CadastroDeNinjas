package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missoes) {
        MissaoDTO novaMissao =  missaoService.criarMissao(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + novaMissao.getNome() + " (ID): " + novaMissao.getId());
    }

    // Listar Missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes(){
        List<MissaoDTO> missoes =  missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // Listar Missao por ID
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoes(@PathVariable Long id) {
        MissaoDTO missao = missaoService.listarMissoesPorId(id);
        if (missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missao com o id: " + id + " nao existe nos nossos registros");
        }
    }

    // Deletar Missao
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {
        if (missaoService.listarMissoesPorId(id) != null){
            missaoService.deletarMissao(id);
            return ResponseEntity.ok("Missão com id: "+ id +", deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com id: " + id + " não encontrado");
        }
    }

    // Atualizar Missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissaoDTO missaoAtualizada) {
        MissaoDTO missao = missaoService.atualizarMissao(id, missaoAtualizada);
        if (missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com o id: " + id + " não existe nos nossos registros");
        }
    }
}

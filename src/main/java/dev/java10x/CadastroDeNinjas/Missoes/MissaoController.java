package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService, MissaoMapper missaoMapper) {
        this.missaoService = missaoService;
    }


    @PostMapping("")
    @Operation(summary = "Cria uma nova missão",  description = "Rota cria uma nova missão e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })
    public ResponseEntity<MissaoDTO> criarMissao(@RequestBody MissaoDTO dto) {
        MissaoDTO novaMissao =  missaoService.criarMissao(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novaMissao);
    }


    @GetMapping("")
    public ResponseEntity<List<MissaoDTO>> listarMissoes(){
        List<MissaoDTO> missoes =  missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Lista missão por id",  description = "Rota lista missão pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Missão não encontrada")
    })
    public ResponseEntity<MissaoDTO> listarPorId(@PathVariable Long id) {
        return missaoService.listarMissoesPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMissao(@PathVariable Long id) {
        if (missaoService.listarMissoesPorId(id).isPresent()) {
            missaoService.deletarMissao(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Altera missão por id", description = "Rota altera dados da missão pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<MissaoDTO> alterar(@PathVariable Long id, @RequestBody MissaoDTO dto) {
        return missaoService.atualizarMissao(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

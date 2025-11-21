package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private final MissoesRepository missoesRepository;
    public final MissaoMapper missaoMapper;

    public MissaoService(MissoesRepository missoesRepository, MissaoMapper missaoMapper) {
        this.missoesRepository = missoesRepository;
        this.missaoMapper = missaoMapper;
    }

    // Criar nova Missao
    public MissaoDTO criarMissao(MissaoDTO missaoDTO) {
        MissaoModel missao = missaoMapper.map(missaoDTO);
        missao = missoesRepository.save(missao);
        return missaoMapper.map(missao);
    }

    // Listar todas as Missoes
    public List<MissaoModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Listar Missoes por ID
    public MissaoModel listarMissoesPorId(Long id) {
        Optional<MissaoModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.orElse(null);
    }

    // Deletar missao
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

    // Atualizar Missao
    public MissaoModel atualizarMissao(Long id, MissaoModel missoesAtualizada) {
        if (missoesRepository.existsById(id)) {
            missoesAtualizada.setId(id);
            return missoesRepository.save(missoesAtualizada);
        }
        return null;
    }
}

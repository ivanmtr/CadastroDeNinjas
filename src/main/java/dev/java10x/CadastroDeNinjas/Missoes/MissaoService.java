package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missoesRepository;
    public final MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missoesRepository, MissaoMapper missaoMapper) {
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
    public List<MissaoDTO> listarMissoes() {
        List<MissaoModel> missoes = missoesRepository.findAll();
        return missoes.stream()
                .map(missaoMapper::map)
                .collect(Collectors.toList());
    }

    // Listar Missoes por ID
    public MissaoDTO listarMissoesPorId(Long id) {
        Optional<MissaoModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missaoMapper::map)
                .orElse(null);
    }

    // Deletar missao
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }

    // Atualizar Missao
    public MissaoDTO atualizarMissao(Long id, MissaoDTO missaoDTO) {
        Optional<MissaoModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissaoModel missaoAtualizada = missaoMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissaoModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missaoMapper.map(missaoSalva);
        }
        return null;
    }
}

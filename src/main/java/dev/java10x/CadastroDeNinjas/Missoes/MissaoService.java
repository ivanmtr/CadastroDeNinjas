package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.Comparator;
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


    public MissaoDTO criarMissao(MissaoDTO missaoDTO) {
        MissaoModel missao = missaoMapper.toModel(missaoDTO);
        missao = missoesRepository.save(missao);
        return missaoMapper.toDTO(missao);
    }


    public List<MissaoDTO> listarMissoes() {
        List<MissaoModel> missoes = missoesRepository.findAll();
        return missoesRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MissaoModel::getDificuldade)) // Ordena pelo Enum
                .map(missaoMapper::toDTO)
                .collect(Collectors.toList());
    }


    public MissaoDTO listarMissoesPorId(Long id) {
        Optional<MissaoModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missaoMapper::toDTO)
                .orElse(null);
    }

    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }


    public MissaoDTO atualizarMissao(Long id, MissaoDTO missaoDTO) {
        Optional<MissaoModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissaoModel missaoAtualizada = missaoMapper.toModel(missaoDTO);
            missaoAtualizada.setId(id);
            MissaoModel missaoSalva = missoesRepository.save(missaoAtualizada);
            return missaoMapper.toDTO(missaoSalva);
        }
        return null;
    }
}

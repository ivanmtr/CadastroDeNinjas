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
        return missoesRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(MissaoModel::getDificuldade))
                .map(missaoMapper::toDTO)
                .collect(Collectors.toList());
    }


    public Optional<MissaoDTO> listarMissoesPorId(Long id) {
        return missoesRepository.findById(id)
                .map(missaoMapper::toDTO);
    }

    public boolean deletarMissao(Long id) {
        if (missoesRepository.existsById(id)) {
            missoesRepository.deleteById(id);
            return true;
        }
        return false;
    }


    public Optional<MissaoDTO> atualizarMissao(Long id, MissaoDTO missaoDTO) {
        return missoesRepository.findById(id)
                .map(missaoExistente -> {
                    MissaoModel missaoAtualizada = missaoMapper.toModel(missaoDTO);
                    missaoAtualizada.setId(id);
                    return missaoMapper.toDTO(missoesRepository.save(missaoAtualizada));
                });
    }
}

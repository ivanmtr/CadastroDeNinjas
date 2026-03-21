package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoDTO;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoMapper;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;
    private final MissaoRepository missaoRepository;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper, MissaoRepository missaoRepository) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
        this.missaoRepository = missaoRepository;
    }


    public List<NinjaDTO> listarNinjas() {
        return ninjaRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(NinjaModel::getId))
                .map(ninjaMapper::map)
                .toList();
    }

    public Optional<NinjaDTO>  listarNinjasPorId(Long id) {
        return ninjaRepository.findById(id)
                .map(ninjaMapper::map);
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);

        if (ninjaDTO.getMissaoId() != null) {
            missaoRepository.findById(ninjaDTO.getMissaoId())
                    .ifPresent(ninja::setMissoes);
        }

        return ninjaMapper.map(ninjaRepository.save(ninja));
    }

    public Optional<NinjaDTO> atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        return ninjaRepository.findById(id)
                .map(ninjaExistente -> {
                    // Atualiza os dados básicos usando o mapper ou manualmente
                    // Dica: Se o mapper for bem configurado, use ninjaMapper.map(ninjaDTO)
                    ninjaExistente.setNome(ninjaDTO.getNome());
                    ninjaExistente.setIdade(ninjaDTO.getIdade());
                    ninjaExistente.setEmail(ninjaDTO.getEmail());
                    ninjaExistente.setRanking(ninjaDTO.getRanking());
                    ninjaExistente.setImgUrl(ninjaDTO.getImgUrl());

                    if (ninjaDTO.getMissaoId() != null) {
                        missaoRepository.findById(ninjaDTO.getMissaoId())
                                .ifPresent(ninjaExistente::setMissoes);
                    } else {
                        ninjaExistente.setMissoes(null);
                    }

                    return ninjaMapper.map(ninjaRepository.save(ninjaExistente));
                });
    }

    public boolean deletarNinjaPorId(Long id) {
        if (ninjaRepository.existsById(id)) {
            ninjaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoDTO;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoMapper;
import dev.java10x.CadastroDeNinjas.Missoes.MissaoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .map(ninjaMapper::map)
                .toList();
    }

    public NinjaDTO listarNinjasPorId(Long id) {
        return ninjaRepository.findById(id)
                .map(ninjaMapper::map)
                .orElse(null);
    }

    public List<MissaoDTO> listarMissoes() {
        return missaoRepository.findAll()
                .stream()
                .map(MissaoMapper::map)
                .toList();
    }

    public List<String> listarRanks() {
        return Arrays.stream(RankNinja.values())
                .map(Enum::name)
                .toList();
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);

        // Resolver missão pelo ID (se houver)
        if (ninjaDTO.getMissaoId() != null) {
            missaoRepository.findById(ninjaDTO.getMissaoId())
                    .ifPresent(ninja::setMissoes);
        } else {
            ninja.setMissoes(null);
        }

        NinjaModel salvo = ninjaRepository.save(ninja);
        return ninjaMapper.map(salvo);
    }

    public NinjaDTO atualizarNinja(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> optionalNinja = ninjaRepository.findById(id);

        if (optionalNinja.isEmpty()) {
            return null;
        }

        NinjaModel ninjaExistente = optionalNinja.get();

        ninjaExistente.setNome(ninjaDTO.getNome());
        ninjaExistente.setIdade(ninjaDTO.getIdade());
        ninjaExistente.setEmail(ninjaDTO.getEmail());
        ninjaExistente.setRank(ninjaDTO.getRank());
        ninjaExistente.setImgUrl(ninjaDTO.getImgUrl());

        if (ninjaDTO.getMissaoId() != null) {
            missaoRepository.findById(ninjaDTO.getMissaoId())
                    .ifPresent(ninjaExistente::setMissoes);
        } else {
            ninjaExistente.setMissoes(null);
        }

        NinjaModel salvo = ninjaRepository.save(ninjaExistente);
        return ninjaMapper.map(salvo);
    }

    public void deletarNinjaPorId(Long id) {
        ninjaRepository.deleteById(id);
    }
}


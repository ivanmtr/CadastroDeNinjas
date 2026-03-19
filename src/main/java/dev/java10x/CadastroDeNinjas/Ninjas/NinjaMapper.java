package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoRepository;
import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    private final MissaoRepository missaoRepository;

    public NinjaMapper(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    // DTO -> Model
    public NinjaModel map(NinjaDTO dto) {
        if (dto == null) return null;

        NinjaModel model = new NinjaModel();

        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade());
        model.setImgUrl(dto.getImgUrl());

        // Rank (Enum)
        if (dto.getRanking() != null) {
            model.setRanking(dto.getRanking());
        }

        // Missão via ID (controlado)
        if (dto.getMissaoId() != null) {
            missaoRepository.findById(dto.getMissaoId())
                    .ifPresent(model::setMissoes);
        } else {
            model.setMissoes(null);
        }

        return model;
    }

    // Model -> DTO
    public NinjaDTO map(NinjaModel model) {
        if (model == null) return null;

        NinjaDTO dto = new NinjaDTO();

        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setImgUrl(model.getImgUrl());

        // Ranking (Enum)
        dto.setRanking(model.getRanking());

        // Missão
        if (model.getMissoes() != null) {
            dto.setMissaoId(model.getMissoes().getId());
            dto.setMissaoNome(model.getMissoes().getNome());
            dto.setMissaoDificuldade(model.getMissoes().getDificuldade());
        }

        return dto;
    }
}

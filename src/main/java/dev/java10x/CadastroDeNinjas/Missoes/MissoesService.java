package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService(MissoesRepository missoesRepository) {
        this.missoesRepository = missoesRepository;
    }

    // Criar nova Missao
    public MissoesModel criarMissao(MissoesModel missoes) {
        return missoesRepository.save(missoes);
    }

    // Listar todas as Missoes
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    // Listar Missoes por ID
    public MissoesModel listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.orElse(null);
    }

    // Deletar missao
    public void deletarMissao(Long id) {
        missoesRepository.deleteById(id);
    }
}

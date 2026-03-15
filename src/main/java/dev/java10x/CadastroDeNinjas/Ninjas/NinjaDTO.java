package dev.java10x.CadastroDeNinjas.Ninjas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {
        private Long id;
        private String nome;
        private String email;
        private String imgUrl;
        private int idade;
        private Rank ranking;

        private Long missaoId;
        private String missaoNome;
        private String missaoDificuldade;
}


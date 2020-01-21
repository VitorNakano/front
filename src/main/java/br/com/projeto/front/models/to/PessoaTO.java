package br.com.projeto.front.models.to;



import lombok.Data;

import java.io.Serializable;

@Data
public class PessoaTO implements Serializable {

    private String nome;
    private String sobrenome;

    public PessoaTO(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
}

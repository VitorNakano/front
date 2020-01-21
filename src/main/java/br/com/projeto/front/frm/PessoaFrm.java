package br.com.projeto.front.frm;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;
import br.com.projeto.front.sevice.PessoaService;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Data
public class PessoaFrm implements Serializable {

    private List<Pessoa> pessoas;

    @Inject
    private PessoaService service;

    @PostConstruct
    public void init() {
        pessoas = new ArrayList<>();
    }

    public void buscarPessoas() {
        pessoas = service.findAll();
    }

    public void cadastrarPessoa(String nome, String sobrenome) {
        if (!nome.isEmpty() && !sobrenome.isEmpty()) {
            PessoaTO pessoaTO = new PessoaTO(nome, sobrenome);
            service.novo(pessoaTO);
        }

    }

}

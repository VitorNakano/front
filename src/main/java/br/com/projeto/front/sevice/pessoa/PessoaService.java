package br.com.projeto.front.sevice;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;

import java.util.List;

public interface PessoaService {

    List<Pessoa> findAll();

    List<Pessoa> pessoasFiltro(String nome);

    Pessoa findByNome(String nome);

    Pessoa findById(Integer idPessoa);

    Pessoa novo(PessoaTO to);

    Pessoa editar(Integer idPessoa, Pessoa p);

    Pessoa excluir(Integer idPessoa);

}
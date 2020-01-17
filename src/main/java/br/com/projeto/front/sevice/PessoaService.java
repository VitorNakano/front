package br.com.projeto.front.sevice;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PessoaService {

    List<Pessoa> findAll();
    Pessoa findByNome(String nome);
    Pessoa findById(Integer idPessoa);
    Pessoa novo(PessoaTO to);
    Pessoa editar(Integer idPessoa, PessoaTO to);
    Pessoa excluir(Integer idPessoa);

}

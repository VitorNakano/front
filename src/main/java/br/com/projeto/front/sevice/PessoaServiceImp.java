package br.com.projeto.front.sevice;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;
import br.com.projeto.front.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaServiceImp implements PessoaService {

    @Autowired
    private final PessoaRepository pessoaDao;

    public PessoaServiceImp(PessoaRepository pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaDao.findAll();
    }

    @Override
    public Pessoa findByNome(String nome) {
        if (nome != null || !nome.isEmpty()) {
            Pessoa pessoa = pessoaDao.findByName(nome);
            if (pessoa != null) {
                validarParametros(pessoa);
                return pessoa;
            } else {
                throw new NullPointerException("Pessoa não Encontrada");
            }
        } else {
            throw new NullPointerException("Nome não encontrado");
        }
    }

    @Override
    public Pessoa findById(Integer idPessoa) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                validarParametros(pessoa);
                return pessoa;
            } else {
                throw new NullPointerException("ID não Encontrado");
            }
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }

    }

    @Override
    public Pessoa novo(PessoaTO to) {
        if (to.getNome() != null || !to.getNome().isEmpty() &&
                to.getSobrenome() != null || !to.getSobrenome().isEmpty()) {
            Pessoa pessoa = new Pessoa(to.getNome(), to.getSobrenome());
            pessoaDao.save(pessoa);
            return pessoa;
        } else {
            throw new NullPointerException("Nome não Encontrado");
        }
    }

    @Override
    public Pessoa editar(Integer idPessoa, PessoaTO to) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                if (to.getNome() != null || !to.getNome().isEmpty() &&
                        to.getSobrenome() != null || !to.getSobrenome().isEmpty()) {
                    pessoa.setFirst_name(to.getNome());
                    pessoa.setLast_name(to.getSobrenome());
                } else {
                    throw new NullPointerException("Pessoa não encontrada.");
                }

            } else {
                throw new NullPointerException("Os campos Nome ou Sobrenome devem sem preenchidos");
            }
            pessoaDao.save(pessoa);
            return pessoa;
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }
    }


    @Override
    public Pessoa excluir(Integer idPessoa) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                validarParametros(pessoa);
                pessoaDao.delete(pessoa);
            }
            return pessoa;
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }
    }

    private boolean validarParametros() {

        if (object != null) {
            return new ResponseEntity<>(object, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

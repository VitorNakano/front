package br.com.projeto.front.sevice;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;
import br.com.projeto.front.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaServiceImp implements PessoaService {

    @Autowired
    private final PessoaRepository pessoaDao;

    public PessoaServiceImp(PessoaRepository pessoaDao) {
        this.pessoaDao = pessoaDao;
    }

    /**
     * Method to lists all Objects of Pessoa.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @return All Objects of Pessoa in Database.
     */
    @Override
    public List<Pessoa> findAll() {
        return pessoaDao.findAll();
    }

    /**
     * Method to find an Object of Pessoa by Name.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @param nome Pamater to search an Object of Pessoa in Database.
     * @return An Object of Pessoa.
     */
    @Override
    public Pessoa findByNome(String nome) {
        if (validarParametros(nome)) {
            Pessoa pessoa = pessoaDao.findByName(nome);
            if (pessoa != null) {
                validarParametros(pessoa);
                return pessoa;
            } else {
                throw new NullPointerException("Pessoa não encontrada.");
            }
        } else {
            throw new NullPointerException("Nome não encontrado.");
        }
    }

    /**
     * Method to find an Object of Pessoa by ID.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @param idPessoa Parameter to search an Object of Pessoa in Database.
     * @return An Object of Pessoa.
     */
    @Override
    public Pessoa findById(Integer idPessoa) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                return pessoa;
            } else {
                throw new NullPointerException("Pessoa não encontrada.");
            }
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }

    }

    /**
     * Method to create a new Object of People and insert the Object into the Database.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @param to Instance of PessoaTO
     * @return An Object of Pessoa
     */
    @Override
    public Pessoa novo(PessoaTO to) {
        if (to != null) {
            if (validarParametros(to.getNome()) && validarParametros(to.getSobrenome())) {
                Pessoa pessoa = new Pessoa(to.getNome(), to.getSobrenome());
                pessoaDao.save(pessoa);
                return pessoa;
            } else {
                throw new NullPointerException("Os campos Nomes e Sobrenome devem ser preenchidos.");
            }
        } else {
            throw new NullPointerException("Pessoa não encontrada.");
        }
    }

    /**
     * Method to make a change in an Object Pessoa by your ID.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @param idPessoa Identifier consulted on Database.
     * @param to       Instance of PessoaTO.
     * @return An Object of Pessoa.
     */
    @Override
    public Pessoa editar(Integer idPessoa, PessoaTO to) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                if (validarParametros(to.getNome())) {
                    pessoa.setFirst_name(to.getNome());
                } else {
                    throw new NullPointerException("O campo Nome deve ser preenchido");
                }
                if (validarParametros(to.getSobrenome())) {
                    pessoa.setLast_name(to.getSobrenome());
                } else {
                    throw new NullPointerException("O campo Sobrenome deve ser preenchido");
                }
                pessoaDao.save(pessoa);
            } else {
                throw new NullPointerException("Pessoa não encontrada.");
            }
            return pessoa;
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }
    }

    /**
     * Method to delete an Object of Pessoa by your ID from the Database.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/17/2020
     *
     * @param idPessoa Identifier consulted on Database.
     * @return An Object of Pessoa.
     */
    @Override
    public Pessoa excluir(Integer idPessoa) {
        if (idPessoa != null) {
            Pessoa pessoa = pessoaDao.findById(idPessoa).orElse(null);
            if (pessoa != null) {
                pessoaDao.delete(pessoa);
            } else {
                throw new NullPointerException("Pessoa não encontrada.");
            }
            return pessoa;
        } else {
            throw new NullPointerException("O campo ID deve ser preenchido.");
        }
    }

    /**
     * Method to validate the received parameters.
     * @Author: Vitor Nakano
     * @Version: 1.0
     * @Date: 01/20/2020
     *
     * @param param A generic parameter.
     * @param <T>   I don't know what is this parameter.
     * @return A boolean.
     */
    private <T> boolean validarParametros(T param) {
        if(param instanceof String) {
            return ((String) param).length() > 0;
        }
        if(param instanceof Integer) {
            return ((Integer) param) > 0;
        }
        

        return false;
    }
}

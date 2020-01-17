package br.com.projeto.front.repository;

import br.com.projeto.front.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query(value = "SELECT * FROM pessoa WHERE first_name = ?", nativeQuery = true)
    Pessoa findByName(String nome);
}

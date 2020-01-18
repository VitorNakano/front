package br.com.projeto.front.endpoint;

import br.com.projeto.front.models.Pessoa;
import br.com.projeto.front.models.to.PessoaTO;
import br.com.projeto.front.sevice.PessoaServiceImp;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PessoaEndpoint {

    private PessoaServiceImp service;

    @Autowired
    public PessoaEndpoint(PessoaServiceImp service) {
        this.service = service;
    }
    
    @GetMapping(path = "/pessoa")
    @ResponseBody
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/pessoa/byId/{id}")
    @ResponseBody
    public ResponseEntity<?> byId(@PathVariable Integer id) {
        Pessoa pessoa = service.findById(id);
        if(validar(pessoa)) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/pessoa/byName/{nome}")
    @ResponseBody
    public ResponseEntity<?> byName(@PathVariable String nome) {
        Pessoa pessoa = service.findByNome(nome);
        if(validar(pessoa)) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/pessoa/nova")
    @ResponseBody
    public ResponseEntity<?> novaPessoa(@RequestBody PessoaTO to) {
        Pessoa pessoa = service.novo(to);
        if(validar(pessoa)) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/pessoa/alterar/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable Integer id,
                                     @RequestBody PessoaTO to) {
        Pessoa pessoa = service.editar(id, to);
        if(validar(pessoa)) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/pessoa/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        Pessoa pessoa = service.excluir(id);
        if(validar(pessoa)) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private boolean validar(Object object) {
        if (object != null) {
            return true;
        } else {
            return false;
        }
    }

}

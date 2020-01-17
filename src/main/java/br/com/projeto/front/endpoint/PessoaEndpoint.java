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
//        return service.findById(id);
        return null;
    }

    @GetMapping(path = "/pessoa/byName/{nome}")
    @ResponseBody
    public ResponseEntity<?> byName(@PathVariable String nome) {
        return new ResponseEntity<>(service.findByNome(nome), HttpStatus.OK);
    }

    @PostMapping(path = "/pessoa/nova")
    @ResponseBody
    public ResponseEntity<?> novaPessoa(@RequestBody PessoaTO to) {
        System.out.println(to);
        return new ResponseEntity<>(service.novo(to), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/pessoa/alterar/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable Integer id,
                                     @RequestBody PessoaTO to) {
        return new ResponseEntity<>(service.editar(id, to), HttpStatus.OK);
    }

    @DeleteMapping(path = "/pessoa/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        return new ResponseEntity<>(service.excluir(id), HttpStatus.OK);
    }



}

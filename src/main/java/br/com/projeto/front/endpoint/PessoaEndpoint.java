package br.com.projeto.front.endpoint;

import br.com.projeto.front.models.to.PessoaTO;
import br.com.projeto.front.sevice.PessoaServiceImp;
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
        return validar(service.findById(id));
    }

    @GetMapping(path = "/pessoa/byName/{nome}")
    @ResponseBody
    public ResponseEntity<?> byName(@PathVariable String nome) {
        return validar(service.findByNome(nome));
    }

    @PostMapping(path = "/pessoa/nova")
    @ResponseBody
    public ResponseEntity<?> novaPessoa(@RequestBody PessoaTO to) {
        return validar(service.novo(to));
    }

    @PatchMapping(path = "/pessoa/alterar/{id}")
    @ResponseBody
    public ResponseEntity<?> alterar(@PathVariable Integer id,
                                     @RequestBody PessoaTO to) {
        return validar(service.editar(id, to));
    }

    @DeleteMapping(path = "/pessoa/{id}")
    @ResponseBody
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        return validar(service.excluir(id));
    }

    private ResponseEntity validar(Object object) {
        if (object != null) {
            return new ResponseEntity<>(object, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

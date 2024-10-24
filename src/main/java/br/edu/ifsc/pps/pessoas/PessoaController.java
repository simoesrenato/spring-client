package br.edu.ifsc.pps.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Adicionar nova pessoa via POST
    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.adicionarPessoa(pessoa);
        return ResponseEntity.ok(novaPessoa);
    }

    // Atualizar pessoa via PUT
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable int id, @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(pessoa);
        if (pessoaAtualizada != null) {
            return ResponseEntity.ok(pessoaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar pessoa via DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable int id) {
        boolean deletada = pessoaService.deletarPessoa(id);
        if (deletada) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar pessoa por ID via GET
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable int id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar pessoas por nome (contains) via GET
    @GetMapping("/buscar")
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam String nome) {
        List<Pessoa> resultado = pessoaService.buscarPorNome(nome);
        return ResponseEntity.ok(resultado);
    }

    // Buscar todas as pessoas via GET
    @GetMapping("/todos")
    public ResponseEntity<List<Pessoa>> buscarTodos() {
        List<Pessoa> resultado = pessoaService.buscarTodos();
        return ResponseEntity.ok(resultado);
    }
}

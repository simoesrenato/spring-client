package br.edu.ifsc.pps.pessoas;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    private List<Pessoa> pessoas = new ArrayList<>();

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        pessoa.setId(pessoas.size()); // Define o ID como o índice da lista
        pessoas.add(pessoa);
        return pessoa;
    }

    public Pessoa atualizarPessoa(Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = buscarPorId(pessoaAtualizada.getId());

        if (pessoaExistente != null) {
            if (!pessoaAtualizada.getNome().equals(pessoaExistente.getNome())) {
                pessoaExistente.setNome(pessoaAtualizada.getNome());
            }
            if (!pessoaAtualizada.getTelefone().equals(pessoaExistente.getTelefone())) {
                pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());
            }
            if (!pessoaAtualizada.getEndereco().equals(pessoaExistente.getEndereco())) {
                pessoaExistente.setEndereco(pessoaAtualizada.getEndereco());
            }
            if (pessoaAtualizada.getIdade() != pessoaExistente.getIdade()) {
                pessoaExistente.setIdade(pessoaAtualizada.getIdade());
            }
            if (pessoaAtualizada.isCasado() != pessoaExistente.isCasado()) {
                pessoaExistente.setCasado(pessoaAtualizada.isCasado());
            }
            return pessoaExistente;
        }
        return null;
    }

    public boolean deletarPessoa(int id) {
        Pessoa pessoa = buscarPorId(id);
        if (pessoa != null) {
            return pessoas.remove(pessoa);
        }
        return false;
    }

    public Pessoa buscarPorId(int id) {
        return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public List<Pessoa> buscarPorNome(String nome) {
        List<Pessoa> resultado = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(pessoa);
            }
        }
        return resultado;
    }
    public List<Pessoa> buscarTodos(){
        return buscarPorNome("");
    }
}

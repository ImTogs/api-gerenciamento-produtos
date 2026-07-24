package com.grupo.produtos.service;

import com.grupo.produtos.exception.ProdutoNotFoundException;
import com.grupo.produtos.model.Produto;
import com.grupo.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutoService {

    private final ProdutoRepository repository;


    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }


    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }



    public List<Produto> listarTodos() {
        return repository.findAll();
    }



    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }



    public Produto atualizar(Long id, Produto dadosNovos) {
        Produto existente = buscarPorId(id); // lança 404 se não existir

        existente.setNome(dadosNovos.getNome());
        existente.setDescricao(dadosNovos.getDescricao());
        existente.setPreco(dadosNovos.getPreco());
        existente.setQuantidade(dadosNovos.getQuantidade());
        existente.setCategoria(dadosNovos.getCategoria());

        return repository.save(existente);
    }



    public void deletar(Long id) {
        Produto existente = buscarPorId(id); // lança 404 se não existir
        repository.delete(existente);
    }



    public List<Produto> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}

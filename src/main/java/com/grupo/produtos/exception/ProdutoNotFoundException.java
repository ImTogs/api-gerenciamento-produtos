package com.grupo.produtos.exception;

/**
 * Exceção lançada quando um produto não é encontrado no banco.
 * Resulta em HTTP 404 Not Found.
 */
public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(Long id) {
        super("Produto não encontrado com id: " + id);
    }
}

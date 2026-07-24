package com.grupo.produtos;

import com.grupo.produtos.exception.ProdutoNotFoundException;
import com.grupo.produtos.model.Produto;
import com.grupo.produtos.repository.ProdutoRepository;
import com.grupo.produtos.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Notebook", "Descrição teste", 2000.00, 5, "Eletrônico");
        produto.setId(1L);
    }

    @Test
    @DisplayName("Deve salvar produto com sucesso")
    void deveSalvarProduto() {
        when(repository.save(any(Produto.class))).thenReturn(produto);

        Produto salvo = service.salvar(produto);

        assertNotNull(salvo);
        assertEquals("Notebook", salvo.getNome());
        verify(repository, times(1)).save(produto);
    }

    @Test
    @DisplayName("Deve retornar todos os produtos")
    void deveListarTodos() {
        when(repository.findAll()).thenReturn(List.of(produto));

        List<Produto> lista = service.listarTodos();

        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar produto pelo ID")
    void deveBuscarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(produto));

        Produto encontrado = service.buscarPorId(1L);

        assertEquals(1L, encontrado.getId());
    }

    @Test
    @DisplayName("Deve lançar ProdutoNotFoundException para ID inexistente")
    void deveLancarExcecaoQuandoNaoEncontrar() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProdutoNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    @DisplayName("Deve deletar produto existente")
    void deveDeletarProduto() {
        when(repository.findById(1L)).thenReturn(Optional.of(produto));
        doNothing().when(repository).delete(produto);

        assertDoesNotThrow(() -> service.deletar(1L));
        verify(repository, times(1)).delete(produto);
    }
}

package com.grupo.produtos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Schema(description = "Representa um produto do catálogo")
@Entity
@Table(name = "produtos")
public class Produto {

    @Schema(description = "Identificador único do produto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome do produto", example = "Notebook Dell Inspiron", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 2, max = 150, message = "O nome deve ter entre 2 e 150 caracteres")
    @Column(nullable = false, length = 150)
    private String nome;

    @Schema(description = "Descrição detalhada do produto", example = "Notebook com processador Intel Core i5, 8GB RAM e SSD 256GB")
    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres")
    @Column(length = 500)
    private String descricao;

    @Schema(description = "Preço unitário em reais", example = "3499.99", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O preço não pode ser nulo")
    @Positive(message = "O preço deve ser maior que zero")
    @Column(nullable = false)
    private Double preco;

    @Schema(description = "Quantidade disponível em estoque", example = "50", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A quantidade não pode ser nula")
    @PositiveOrZero(message = "A quantidade não pode ser negativa")
    @Column(nullable = false)
    private Integer quantidade;

    @Schema(description = "Categoria do produto", example = "Eletrônico")
    @Column(length = 80)
    private String categoria;



    public Produto() {}

    public Produto(String nome, String descricao, Double preco, Integer quantidade, String categoria) {
        this.nome       = nome;
        this.descricao  = descricao;
        this.preco      = preco;
        this.quantidade = quantidade;
        this.categoria  = categoria;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "Produto{id=" + id + ", nome='" + nome + "', preco=" + preco + "}";
    }
}

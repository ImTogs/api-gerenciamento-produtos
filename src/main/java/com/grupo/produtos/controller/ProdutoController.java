package com.grupo.produtos.controller;

import com.grupo.produtos.model.Produto;
import com.grupo.produtos.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Tag(name = "Produtos", description = "Operações CRUD para gerenciamento do catálogo de produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }



    @Operation(
        summary     = "Inserir novo produto",
        description = "Cria um novo produto no catálogo. O campo 'id' é gerado automaticamente e não deve ser informado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
            content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos (campos obrigatórios ausentes ou formato incorreto)",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Produto> inserir(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Dados do produto a ser cadastrado",
                required = true)
            @Valid @RequestBody Produto produto) {

        Produto salvo = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }


    @Operation(
        summary     = "Listar todos os produtos",
        description = "Retorna a lista completa de produtos cadastrados no catálogo."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso (pode estar vazia)",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Produto.class))))
    })
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }


    @Operation(
        summary     = "Buscar produto por ID",
        description = "Retorna os dados de um produto específico a partir do seu identificador único."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado para o ID informado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(
            @Parameter(description = "ID do produto", example = "1", required = true)
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }


    @Operation(
        summary     = "Atualizar produto",
        description = "Atualiza todos os campos de um produto existente. O ID no path deve corresponder ao produto desejado."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos na requisição",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado para o ID informado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @Parameter(description = "ID do produto a atualizar", example = "1", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Novos dados do produto",
                required = true)
            @Valid @RequestBody Produto produto) {

        return ResponseEntity.ok(service.atualizar(id, produto));
    }



    @Operation(
        summary     = "Deletar produto",
        description = "Remove permanentemente um produto do catálogo pelo seu ID."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso (sem conteúdo)"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado para o ID informado",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto a deletar", example = "1", required = true)
            @PathVariable Long id) {

        service.deletar(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }



    @Operation(summary = "Buscar produtos por categoria", description = "Retorna todos os produtos de uma determinada categoria.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(
            @Parameter(description = "Nome da categoria", example = "Eletrônico")
            @PathVariable String categoria) {
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }

    @Operation(summary = "Buscar produtos por nome", description = "Retorna produtos cujo nome contém o termo informado (busca parcial, sem distinção de maiúsculas).")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> buscarPorNome(
            @Parameter(description = "Trecho do nome a pesquisar", example = "note")
            @RequestParam String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }


    @Schema(description = "Resposta de erro padrão da API")
    static class ErrorResponse {
        @Schema(example = "404") public int status;
        @Schema(example = "Produto não encontrado com id: 5") public String mensagem;
        @Schema(example = "2024-01-15T10:30:00") public String timestamp;
    }
}

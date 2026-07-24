package com.grupo.produtos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title       = "API de Gerenciamento de Produtos",
        version     = "1.0.0",
        description = "API RESTful desenvolvida com Spring Boot para gerenciar um catálogo de produtos. "
                    + "Implementa as operações CRUD completas (Create, Read, Update, Delete). "
                    + "Projeto da disciplina de Programação Orientada a Objetos.",
        contact = @Contact(
            name  = "Grupo POO",
            email = "grupo@universidade.edu.br"
        ),
        license = @License(
            name = "Apache 2.0",
            url  = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    )
)
@SpringBootApplication
public class ProdutosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutosApiApplication.class, args);
    }
}

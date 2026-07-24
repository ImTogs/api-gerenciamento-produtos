package com.grupo.produtos.config;

import com.grupo.produtos.model.Produto;
import com.grupo.produtos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Popula o banco H2 com dados de exemplo ao iniciar a aplicação.
 * Útil para testes sem precisar inserir dados manualmente.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner carregarDados(ProdutoRepository repo) {
        return args -> {
            repo.save(new Produto("Notebook Dell Inspiron 15",
                    "Notebook Intel Core i5, 8GB RAM, SSD 256GB, tela 15.6\"",
                    3499.99, 10, "Eletrônico"));

            repo.save(new Produto("Mouse Logitech MX Master 3",
                    "Mouse sem fio ergonômico, sensor 4000 DPI, 7 botões programáveis",
                    449.90, 35, "Eletrônico"));

            repo.save(new Produto("Cadeira Gamer ThunderX3",
                    "Cadeira com suporte lombar, encosto reclinável 180°, base metálica",
                    1299.00, 8, "Móvel"));

            repo.save(new Produto("Fone JBL Tune 510BT",
                    "Fone Bluetooth on-ear, bateria 40h, dobráveis",
                    249.99, 50, "Eletrônico"));

            repo.save(new Produto("Caneca Térmica Stanley",
                    "Caneca de 473ml, mantém temperatura por 12h, aço inox",
                    189.00, 25, "Utilidade"));

            System.out.println("\n>>> Banco populado com 5 produtos de exemplo. <<<\n");
        };
    }
}

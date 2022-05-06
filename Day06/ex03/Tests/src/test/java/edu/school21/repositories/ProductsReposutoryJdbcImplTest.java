package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductsReposutoryJdbcImplTest {
   final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
           new Product(1L, "Laptop", 1000),
           new Product(2L, "TV", 1500),
           new Product(3L, "Desktop", 2000),
           new Product(4L, "Mouse", 10),
           new Product(5L, "Printer", 500)
   );
   final Product EXPECTED_FIND_BY_ID_PRODUCT = EXPECTED_FIND_ALL_PRODUCTS.get(2);
   final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Monitor", 2500);
   final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "CoffeeMachine", 700);
    final List<Product> EXPECTED_DELETE_PRODUCTS = Arrays.asList(
            new Product(2L, "TV", 1500),
            new Product(3L, "Desktop", 2000),
            new Product(4L, "Mouse", 10),
            new Product(5L, "Printer", 500)
    );

    private EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
            .generateUniqueName(true)
            .setType(EmbeddedDatabaseType.HSQL)
            .setScriptEncoding("UTF-8")
            .ignoreFailedDrops(true)
            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();

    final ProductsRepositoryJdbcImpl productsRepositoryJdbc = new ProductsRepositoryJdbcImpl(dataSource);

    @Test
    void findAllTest(){
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, new ProductsRepositoryJdbcImpl(dataSource).findAll());
    }

    @Test
    void findByIdTest(){
        Product product = productsRepositoryJdbc.findById(3L).get();
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product);
    }

    @Test
    void updateTest(){
        Product product = productsRepositoryJdbc.findById(3L).get();
        product.setName("Monitor");
        product.setPrice(2500);
        productsRepositoryJdbc.update(product);
        product = productsRepositoryJdbc.findById(3L).get();
        assertEquals(EXPECTED_UPDATED_PRODUCT, product);
    }

    @Test
    void saveTest(){
        productsRepositoryJdbc.save(EXPECTED_SAVED_PRODUCT);
        Product product = productsRepositoryJdbc.findById(6L).get();
        assertEquals(EXPECTED_SAVED_PRODUCT, product);
    }

    @Test
    void deleteTest(){
        productsRepositoryJdbc.delete(1L);
        assertEquals(EXPECTED_DELETE_PRODUCTS, new ProductsRepositoryJdbcImpl(dataSource).findAll());
    }
}

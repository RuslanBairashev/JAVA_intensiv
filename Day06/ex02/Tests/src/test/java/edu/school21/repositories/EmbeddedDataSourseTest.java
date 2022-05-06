package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmbeddedDataSourseTest {

    DataSource dataSource = new EmbeddedDatabaseBuilder()
            .addScript("schema.sql")
            .addScripts("data.sql")
            .build();

    @BeforeEach
    @Test
    void init(){
        try {
            assertNotNull(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

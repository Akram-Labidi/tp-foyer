package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:autoloc;DB_CLOSE_DELAY=-1;MODE=MySQL",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class AutolocApiApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void createsAllDomainTables() {
        List<String> tableNames = List.of(
                "vehicule", "agence", "client", "employe", "equipement",
                "reservation", "contrat", "paiement", "maintenance"
        );

        for (String tableName : tableNames) {
            assertEquals(0, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tableName, Integer.class),
                    "Expected the " + tableName + " table to be created");
        }
    }

}

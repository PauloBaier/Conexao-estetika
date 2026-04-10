package Config;

import org.flywaydb.core.Flyway;
import jakarta.persistence.*;

public class FlyWayConfig {
    public static void migrate() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/conexao",
                        "postgres",
                        "1234"
                )
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();

        System.out.println("Migration executada!");
    }
}

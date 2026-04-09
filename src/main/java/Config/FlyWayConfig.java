package Config;

import org.flywaydb.core.Flyway;

public class FlyWayConfig {
    public static void migrate() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:5432/conexaoestetica",
                        "postgres",
                        "1234"
                )
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();

        System.out.println("Migration executada!");
    }
}

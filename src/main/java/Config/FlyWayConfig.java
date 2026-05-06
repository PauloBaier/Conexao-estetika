package Config;

import org.flywaydb.core.Flyway;

public class FlyWayConfig {

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://localhost:3600/conexaoestetica",
                        "admin",
                        "admin"
                )
                .baselineOnMigrate(true)
                .baselineVersion("4")
                .validateOnMigrate(false)
                .load();

        flyway.migrate();

        System.out.println("Migração executada com sucesso!");
    }
}
package io.dataease.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;


public class DynamicCaseNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {
    private final ThreadLocal<String> currentTableName = new ThreadLocal<>();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        currentTableName.set(name.getText());
        if (name.getText().startsWith("QRTZ_")) {
            return Identifier.toIdentifier(name.getText().toUpperCase());
        } else {
            return name;
        }

    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (currentTableName.get().startsWith("QRTZ_")) {
            return Identifier.toIdentifier(name.getText().toUpperCase());
        } else {
            return name;
        }

    }
}

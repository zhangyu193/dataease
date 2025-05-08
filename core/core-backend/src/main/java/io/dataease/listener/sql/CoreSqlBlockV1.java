package io.dataease.listener.sql;


import io.dataease.initSql.Version;
import org.springframework.stereotype.Component;

@Component
public class CoreSqlBlockV1 implements CoreSqlBlock {

    @Override
    public Version getVersion() {
        return new Version("2.10.9");
    }

    public void execute() {
        System.out.println("执行版本 2.10.9 的代码块");
    }
}

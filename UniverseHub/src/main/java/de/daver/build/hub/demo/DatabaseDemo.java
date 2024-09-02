package de.daver.build.hub.demo;

import de.daver.build.hub.api.sql.Column;
import de.daver.build.hub.api.sql.ColumnType;
import de.daver.build.hub.api.sql.Condition;
import de.daver.build.hub.api.sql.Statement;

public class DatabaseDemo implements Demo {

    public static void main(final String[] args) {
        DatabaseDemo demo = new DatabaseDemo();

        demo.demo();
    }

    @Override
    public void demo() {

        String sql1 = Statement.createTable("world")
                .ifNotExists(true)
                .column(new Column("id", new ColumnType.Varchar(255), true))
                .column(new Column("name", new ColumnType.Varchar(255)))
                .build();

        System.out.println(sql1);

        String sql2 = Statement.insertInto("world")
                .column("id")
                .column("name")
                .build();

        System.out.println(sql2);

        String sql3 = Statement.deleteFrom("world")
                .where(new Condition.Bool("id", "=", 3))
                .build();

        System.out.println(sql3);

        String sql4 = Statement.update("world")
                .column("name")
                .column("id")
                .where(new Condition.Bool("id", "=", 3))
                .build();

        System.out.println(sql4);

        String sql5 = Statement.select()
                .from("world")
                .where(new Condition.Bool("loaded", "=", true))
                .build();

        System.out.println(sql5);
    }
}

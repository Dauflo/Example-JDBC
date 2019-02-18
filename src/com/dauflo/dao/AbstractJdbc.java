package com.dauflo.dao;

import java.sql.Connection;

public abstract class AbstractJdbc {
    protected static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mysql://localhost/paradise";

    protected static final String USER = "root";
    protected static final String PASS = "root";

    protected Connection connection;
}

package org.easy.query.core.basic.jdbc.con;

import org.easy.query.core.exception.EasyQueryException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @FileName: DefaultEasyConnection.java
 * @Description: 文件说明
 * @Date: 2023/2/21 09:26
 * @Created by xuejiaming
 */
public class DefaultEasyConnection implements EasyConnection {
    private final Connection connection;
    private final Integer isolationLevel;
    private  Integer originalIsolationLevel;
    private boolean closed = false;
    private boolean autoCommit;

    public DefaultEasyConnection(Connection connection, Integer isolationLevel) {

        this.connection = connection;
        this.isolationLevel = isolationLevel;
        setIsolationLevel();
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
    private void setIsolationLevel(){
        if(!closed){
            if(isolationLevel!=null&&this.originalIsolationLevel==null){
                try {
                    this.originalIsolationLevel = connection.getTransactionIsolation();
                    if(!this.isolationLevel.equals(originalIsolationLevel)){
                        connection.setTransactionIsolation(isolationLevel);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            this.autoCommit = autoCommit;
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new EasyQueryException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new EasyQueryException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new EasyQueryException(e);
        }
    }

    @Override
    public boolean isClosed() {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            throw new EasyQueryException(e);
        }
    }

    @Override
    public void close() {
        close(true);
    }
    public void close(boolean closeConnection){
        if (closed) {
            return;
        }
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    if (!autoCommit) {
                        connection.setAutoCommit(true);
                    }
                    if (this.originalIsolationLevel!=null&&!Objects.equals(this.isolationLevel,this.originalIsolationLevel)) {
                        connection.setTransactionIsolation(this.isolationLevel);
                    }
                    if(closeConnection){
                        connection.close();
                    }
                }
            } catch (SQLException ex) {
                System.err.println("close connection error: "+ ex.getMessage());

            }
        }
        closed = true;
    }
}
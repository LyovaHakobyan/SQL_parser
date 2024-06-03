package org.example.model;

public class Source {
    private String tableName;

    public Source(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "Source{" +
                "tableName='" + tableName + '\'' +
                '}';
    }
}
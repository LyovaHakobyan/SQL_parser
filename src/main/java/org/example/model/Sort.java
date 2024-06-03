package org.example.model;

public class Sort {
    private String columnName;
    private String direction;

    public Sort(String columnName, String direction) {
        this.columnName = columnName;
        this.direction = direction;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "columnName='" + columnName + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}

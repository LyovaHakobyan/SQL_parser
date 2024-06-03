package org.example.model;

public class Join {
    private String joinType;
    private String tableName;
    private String condition;

    public Join(String joinType, String tableName, String condition) {
        this.joinType = joinType;
        this.tableName = tableName;
        this.condition = condition;
    }

    public String getJoinType() {
        return joinType;
    }

    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Join{" +
                "joinType='" + joinType + '\'' +
                ", tableName='" + tableName + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}

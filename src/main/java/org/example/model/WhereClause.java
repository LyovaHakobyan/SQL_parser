package org.example.model;

public class WhereClause {
    private String condition;

    public WhereClause(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "WhereClause{" +
                "condition='" + condition + '\'' +
                '}';
    }
}

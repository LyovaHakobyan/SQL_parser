package org.example.model;

import java.util.List;

public class Query {
    private List<String> columns;
    private List<Source> fromSources;
    private List<Join> joins;
    private List<WhereClause> whereClauses;
    private List<String> groupByColumns;
    private List<Sort> sortColumns;
    private Integer limit;
    private Integer offset;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Source> getFromSources() {
        return fromSources;
    }

    public void setFromSources(List<Source> fromSources) {
        this.fromSources = fromSources;
    }

    public List<Join> getJoins() {
        return joins;
    }

    public void setJoins(List<Join> joins) {
        this.joins = joins;
    }

    public List<WhereClause> getWhereClauses() {
        return whereClauses;
    }

    public void setWhereClauses(List<WhereClause> whereClauses) {
        this.whereClauses = whereClauses;
    }

    public List<String> getGroupByColumns() {
        return groupByColumns;
    }

    public void setGroupByColumns(List<String> groupByColumns) {
        this.groupByColumns = groupByColumns;
    }

    public List<Sort> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<Sort> sortColumns) {
        this.sortColumns = sortColumns;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Query{" +
                "columns=" + columns +
                ", fromSources=" + fromSources +
                ", joins=" + joins +
                ", whereClauses=" + whereClauses +
                ", groupByColumns=" + groupByColumns +
                ", sortColumns=" + sortColumns +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}
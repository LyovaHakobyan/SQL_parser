package org.example;

import org.example.model.Join;
import org.example.model.Query;
import org.example.model.Sort;
import org.example.model.Source;
import org.example.model.WhereClause;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlQueryParser {

    public static Query parseQuery(String query) {
        Query realQuery = new Query();

        realQuery.setColumns(parseColumns(query));
        realQuery.setFromSources(parseFromSources(query));
        realQuery.setJoins(parseJoins(query));
        realQuery.setWhereClauses(parseWhereClauses(query));
        realQuery.setGroupByColumns(parseGroupByColumns(query));
        realQuery.setSortColumns(parseOrderByColumns(query));
        realQuery.setLimit(parseLimit(query));
        realQuery.setOffset(parseOffset(query));

        return realQuery;
    }

    private static List<String> parseColumns(String query) {
        List<String> colums = new ArrayList<>();
        Matcher matcher = Pattern.compile("select\\s+(.*?)\\s+from").matcher(query);
        if (matcher.find()) {
            String columnsPart = matcher.group(1).trim();
            for (String column : columnsPart.split(",")) {
                colums.add(column.trim());
            }
        }
        return colums;
    }

    private static List<Source> parseFromSources(String query) {
        List<Source> sources = new ArrayList<>();
        Matcher matcher = Pattern.compile("from\\s+(.*?)(\\s+where|\\s+join|\\s+group by|\\s+order by|\\s+limit|\\s*$)").matcher(query);
        if (matcher.find()) {
            String from = matcher.group(1).trim();
            for (String source : from.split(",")) {
                sources.add(parseSource(source.trim()));
            }
        }
        return sources;
    }

    private static Source parseSource(String source) {
        return new Source(source.trim());
    }

    private static List<Join> parseJoins(String query) {
        List<Join> joins = new ArrayList<>();
        Matcher matcher = Pattern.compile("(inner|left|right|full)?\\s*join\\s+(.*?)\\s+on\\s+(.*?)(\\s+where|\\s+join|\\s+group by|\\s+order by|\\s+limit|\\s*$)").matcher(query);
        while (matcher.find()) {
            String joinType;
            if (matcher.group(1) != null) {
                joinType = matcher.group(1).trim();
            } else {
                joinType = "inner";
            }
            String tablePart = matcher.group(2).trim();
            String condition = matcher.group(3).trim();
            joins.add(new Join(joinType, tablePart, condition));
        }
        return joins;
    }

    private static List<WhereClause> parseWhereClauses(String query) {
        List<WhereClause> whereClauses = new ArrayList<>();
        Matcher matcher = Pattern.compile("where\\s+(.*?)(\\s+group by|\\s+order by|\\s+limit|\\s*$)").matcher(query);
        if (matcher.find()) {
            String wherePart = matcher.group(1).trim();
            for (String condition : wherePart.split("\\s+and\\s+")) {
                whereClauses.add(new WhereClause(condition.trim()));
            }
        }
        return whereClauses;
    }

    private static List<String> parseGroupByColumns(String query) {
        List<String> groupByColumns = new ArrayList<>();
        Matcher matcher = Pattern.compile("group by\\s+(.*?)(\\s+order by|\\s+limit|\\s*$)").matcher(query);
        if (matcher.find()) {
            String groupByPart = matcher.group(1).trim();
            for (String column : groupByPart.split(",")) {
                groupByColumns.add(column.trim());
            }
        }
        return groupByColumns;
    }

    private static Integer parseLimit(String query) {
        Matcher matcher = Pattern.compile("limit\\s+(\\d+)").matcher(query);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

    private static List<Sort> parseOrderByColumns(String query) {
        List<Sort> sortColumns = new ArrayList<>();
        Matcher matcher = Pattern.compile("order by\\s+(.*?)(\\s+limit|\\s*$)").matcher(query);
        if (matcher.find()) {
            String orderByPart = matcher.group(1).trim();
            for (String column : orderByPart.split(",")) {
                String[] parts = column.trim().split("\\s+");
                String columnName = parts[0];
                String direction;
                if (parts.length > 1) {
                    direction = parts[1];
                } else {
                    direction = "asc";
                }
                sortColumns.add(new Sort(columnName, direction));
            }
        }
        return sortColumns;
    }

    private static Integer parseOffset(String query) {
        Matcher matcher = Pattern.compile("offset\\s+(\\d+)").matcher(query);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return null;
    }

    public static void main(String[] args) {

        String someQuery = "select author.name, count(book.id), sum(book.cost) from author left join book on (author.id = book.author_id) where author.name = 'John Doe' group by author.name having COUNT(*) > 1 and SUM(book.cost) > 500 order by author.name desc limit 10 offset 5";
        Query query = parseQuery(someQuery);

        System.out.println("Columns - " + query.getColumns());
        System.out.println("From Sources - " + query.getFromSources());
        System.out.println("Joins - " + query.getJoins());
        System.out.println("Where Clauses - " + query.getWhereClauses());
        System.out.println("Group By Columns - " + query.getGroupByColumns());
        System.out.println("Sort Columns - " + query.getSortColumns());
        System.out.println("Limit - " + query.getLimit());
        System.out.println("Offset - " + query.getOffset());
    }
}


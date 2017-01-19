package com.exasol.adapter.sql;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stm on 19.01.17.
 */
public class SqlJoin extends SqlNode {

    private List<SqlNode> parentTables;
    private List<SqlNode> adjoinedTables;
    private SqlJoinType joinType;
    private SqlNode condition;

    @Override
    public SqlNodeType getType() {
        return SqlNodeType.JOIN;
    }

    @Override
    public <R> R accept(SqlNodeVisitor<R> visitor) {
        return visitor.visit(this);
    }


    public SqlJoin(List<SqlNode> parentTables, List<SqlNode> adjoinedTables, SqlJoinType joinType, SqlNode condition) {
        this.parentTables = parentTables;
        this.adjoinedTables = adjoinedTables;
        this.joinType = joinType;
        this.condition = condition;
    }

    public List<SqlNode> getParentTables() {
        return parentTables;
    }

    public List<SqlNode> getAdjoinedTables() {
        return adjoinedTables;
    }

    public SqlJoinType getJoinType() {
        return joinType;
    }

    public SqlNode getCondition() {
        return condition;
    }

    @Override
    String toSimpleSql() {
        List<String> parentList = new ArrayList<>();
        for (SqlNode node : parentTables) {
            parentList.add(node.toSimpleSql());
        }
        String parents = Joiner.on(", ").join(parentList);
        List<String> adjoinList = new ArrayList<>();
        for (SqlNode node : adjoinedTables) {
            adjoinList.add(node.toSimpleSql());
        }
        String adjoins = Joiner.on(", ").join(adjoinList);
        String type = joinType.toString();
        String condition = getCondition().toSimpleSql();

        return parents + " " + type + " JOIN " + adjoins + " ON " + condition;
    }

}

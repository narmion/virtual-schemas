package com.exasol.adapter.sql;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stm on 19.01.17.
 */
public class SqlJoinedTable extends SqlNode{

    private List<SqlNode> joins;

    public SqlJoinedTable(List<SqlNode> joins) {
        this.joins = joins;
    }

    public List<SqlNode> getJoins() {
        return joins;
    }

    @Override
    public SqlNodeType getType() {
        return SqlNodeType.JOINED_TABLE;
    }

    @Override
    public <R> R accept(SqlNodeVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    String toSimpleSql() {
        List<String> joinList = new ArrayList<>();
        for (SqlNode node : joins) {
            joinList.add(node.toSimpleSql());
        }
        return Joiner.on(", ").join(joinList);
    }
}


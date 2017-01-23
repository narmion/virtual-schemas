package com.exasol.adapter.sql;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stm on 19.01.17.
 */
public class SqlJoin extends SqlNode {

    private SqlNode left;
    private SqlNode right;
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


    public SqlJoin(SqlNode left, SqlNode right, SqlJoinType joinType, SqlNode condition) {
        this.left = left;
        this.right = right;
        this.joinType = joinType;
        this.condition = condition;
    }

    public SqlNode getLeft() {
        return left;
    }

    public SqlNode getRight() {
        return right;
    }

    public SqlJoinType getJoinType() {
        return joinType;
    }

    public SqlNode getCondition() {
        return condition;
    }

    @Override
    String toSimpleSql() {
        StringBuilder builder = new StringBuilder();
        builder.append(left.toSimpleSql());
        builder.append(" ");
        builder.append(joinType.toString());
        builder.append(" JOIN ");
        builder.append(right.toSimpleSql());
        builder.append(" ON ");
        builder.append(condition.toSimpleSql());
        return builder.toString();
    }

}

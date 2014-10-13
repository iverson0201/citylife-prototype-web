package com.citylife.backend;

import static org.springframework.data.mongodb.core.query.Query.query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import cz.jirutka.rsql.parser.model.Comparison;
import cz.jirutka.rsql.parser.model.ComparisonExpression;
import cz.jirutka.rsql.parser.model.Expression;
import cz.jirutka.rsql.parser.model.LogicalExpression;

/**
 * @author 作者 E-mail:xujw0201@gmail.com
 * @version 创建时间：2014年9月19日 下午3:22:11
 * 
 */
public class Express2QL {

    public static Query getQL(Expression expression) {
        return query(parse(expression));
    }

    private static Criteria parse(Expression expression)
            throws IllegalArgumentException {

        if (expression.isLogical()) {
            return parse((LogicalExpression) expression);
        }
        if (expression.isComparison()) {
            return parse((ComparisonExpression) expression);
        }

        throw new IllegalArgumentException("Unknown expression type: " + expression.getClass());
    }

    private static Criteria parse(LogicalExpression logical)
            throws IllegalArgumentException {

        switch (logical.getOperator()) {
            case AND : return parse(logical.getLeft()).andOperator(parse(logical.getRight()));

            case OR : return parse(logical.getLeft()).orOperator(parse(logical.getRight()));
        }

        throw new IllegalArgumentException("Unknown operator: " + logical.getOperator());
    }

    private static Criteria parse(ComparisonExpression comparison) {

        String selector = comparison.getSelector();
        String arg = comparison.getArgument();
        Comparison operator = comparison.getOperator();

        Object argument;
        if (StringUtils.isNumeric(arg)) {
            argument = Long.parseLong(arg);
        } else {
            argument = arg;
        }

        switch (operator) {
            case EQUAL : {
                return createEqual(selector, argument);
            }
            case NOT_EQUAL : {
                return createNotEqual(selector, argument);
            }
            case GREATER_THAN : return createGreaterThan(selector, argument);
            case GREATER_EQUAL : return createGreaterEqual(selector, argument);
            case LESS_THAN : return createLessThan(selector, argument);
            case LESS_EQUAL : return createLessEqual(selector, argument);
        }

        throw new IllegalArgumentException("Unknown operator: " + operator);
    }

    private static Criteria createEqual(String key, Object value) {
    	if(!(value instanceof String)){
    		return Criteria.where(key).is(value.toString());
    	}
        return Criteria.where(key).is(value);
    }

    private static Criteria createNotEqual(String key, Object value) {
        return Criteria.where(key).ne(value);
    }

    private static Criteria createGreaterThan(String key, Object value) {
        return Criteria.where(key).gt(value);
    }

    private static Criteria createGreaterEqual(String key, Object value) {
        return Criteria.where(key).gte(value);
    }

    private static Criteria createLessThan(String key, Object value) {
        return Criteria.where(key).lt(value);
    }

    private static Criteria createLessEqual(String key, Object value) {
        return Criteria.where(key).lte(value);
    }
}

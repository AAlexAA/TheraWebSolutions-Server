package com.thera.TheraWebSolutionsServer.model.query;


import lombok.Getter;
import lombok.Setter;

/**
 * @author aniubo
 */
@Setter
@Getter
public class QueryCriteria {

    private String key;
    private Object value;
    private QueryOperator operation;
    private LogicalOperator logicalOperator;
    private Class treat;
    private boolean usingTreatToPath;

    public QueryCriteria() {
    }

    public QueryCriteria(String key, Object value, QueryOperator operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        logicalOperator = LogicalOperator.AND;
        usingTreatToPath = false;
    }

    public QueryCriteria(String key, Object value, QueryOperator operation, Class treat, boolean usingTreatToPath) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        logicalOperator = LogicalOperator.AND;
        this.treat = treat;
        this.usingTreatToPath = usingTreatToPath;
    }

    public QueryCriteria(String key, Object value, QueryOperator operation, Class treat) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        logicalOperator = LogicalOperator.AND;
        this.treat = treat;
        usingTreatToPath = false;
    }

    public QueryCriteria(String key, Object value, QueryOperator operation, LogicalOperator logicalOperator) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        this.logicalOperator = logicalOperator;
        usingTreatToPath = false;
    }
}

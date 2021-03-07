package com.thera.TheraWebSolutionsServer.model.query;

import com.thera.TheraWebSolutionsServer.model.query.QueryCriteria;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author aniubo
 */
@Getter
@Setter
public class QuerySpecification<T> implements Specification<T> {

    private List<QueryCriteria> criterias;
    private HashMap<String, Join<T, ?>> listJoin;
    private List<String> joinList;

    public QuerySpecification() {
        criterias = new ArrayList<>();
        listJoin = new HashMap<>();
        joinList = new ArrayList<>();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate resultPredicate = null;
        List<QueryCriteria> andCriterias = criterias.stream().filter(queryCriteria -> {
            return queryCriteria.getLogicalOperator() == LogicalOperator.AND;
        }).collect(Collectors.toList());
        Predicate andPredicate = getPredicate(andCriterias, root, cq, cb);
        List<QueryCriteria> orCriterias = criterias.stream().filter(queryCriteria -> {
            return queryCriteria.getLogicalOperator() == LogicalOperator.OR;
        }).collect(Collectors.toList());
        Predicate orPredicate = getPredicate(orCriterias, root, cq, cb);

        return andPredicate != null && orPredicate != null ? cb.and(andPredicate, orPredicate)
                : andPredicate != null ? andPredicate : orPredicate;
    }

    private Predicate getPredicate(List<QueryCriteria> group, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate resultPredicate = null;
        for (QueryCriteria criteria : group) {
            Predicate predicate = null;
            Path path = null;
            if (criteria.isUsingTreatToPath() && criteria.getTreat() != null) {
                Root treatRoot = cb.treat(root, criteria.getTreat());
                path = getPath(treatRoot, criteria.getKey());
            } else {
                path = getPath(root, criteria.getKey());
            }
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    if (path != null) {
                        if (criteria.getValue() instanceof Date) {
                            predicate = cb.greaterThan(path, (Date) criteria.getValue());
                            break;
                        }
                        predicate = cb.greaterThan(path, criteria.getValue().toString());
                    }
                    break;
                case LESS_THAN:
                    if (path != null) {
                        if (criteria.getValue() instanceof Date) {
                            predicate = cb.lessThan(path, (Date) criteria.getValue());
                            break;
                        }
                        predicate = cb.lessThan(path, criteria.getValue().toString());
                    }
                    break;
                case GREATER_THAN_EQUAL:
                    if (path != null) {
                        if (criteria.getValue() instanceof Date) {
                            predicate = cb.greaterThanOrEqualTo(path, (Date) criteria.getValue());
                            break;
                        }
                        predicate = cb.greaterThanOrEqualTo(
                                path, criteria.getValue().toString());
                    }
                    break;
                case LESS_THAN_EQUAL:
                    if (path != null) {
                        if (criteria.getValue() instanceof Date) {
                            predicate = cb.lessThanOrEqualTo(path, (Date) criteria.getValue());
                            break;
                        }
                        predicate = cb.lessThanOrEqualTo(
                                path, criteria.getValue().toString());
                    }
                    break;
                case NOT_EQUAL:
                    if (path != null) {
                        predicate = cb.notEqual(
                                path, criteria.getValue());
                    }
                    break;
                case EQUAL:
                    if (path != null) {
                        predicate = cb.equal(
                                path, criteria.getValue());
                    }
                    break;
                case MATCH:
                    if (path != null) {
                        predicate = cb.like(
                                cb.lower(path),
                                "%" + criteria.getValue().toString().toLowerCase() + "%");
                    }
                    break;
                case MATCH_END:
                    if (path != null) {
                        predicate = cb.like(
                                cb.lower(path),
                                criteria.getValue().toString().toLowerCase() + "%");
                    }

                    break;
                case JOIN_FETCH:
                    root.fetch(criteria.getKey(), JoinType.INNER);
                    break;
                case JOIN_LEFT:
                    root.fetch(criteria.getKey(), JoinType.LEFT);
                    break;
                case JOIN_LIST:
                    Join<T, ?> join = root.join(criteria.getKey());
                    if (criteria.getTreat() != null) {
                        Join<T, ?> joinTreat = cb.treat(join, criteria.getTreat());
                        listJoin.put(criteria.getKey(), joinTreat);
                    } else {
                        listJoin.put(criteria.getKey(), join);
                    }
                    break;
                case IN:
                    if (path != null) {
                        predicate = cb.in(path).value(criteria.getValue());
                        predicates.add(predicate);
                    }
                    break;

                case BETWEEN:
                    if (path != null) {
                        predicate = cb.between(path, ((List<Date>) criteria.getValue()).get(0),
                                ((List<Date>) criteria.getValue()).get(1));
                        predicates.add(predicate);
                    }
                    break;
                case DISTINCT:
                    cq.distinct(true);
                    break;
                case ORDER_BY_ASC:
                    if (path != null) {
                        cq.orderBy(cb.asc(path));
                    }
                    break;
                case ORDER_BY_DESC:
                    if (path != null) {
                        cq.orderBy(cb.desc(path));
                    }
                    break;
                case IS_NOT_NULL:
                    if (path != null) {
                        predicate = cb.isNotNull(path);
                    }
                    break;
                case IS_NULL:
                    if (path != null) {
                        predicate = cb.isNull(path);
                    }
                    break;
                default:
                    break;
            }
            predicates.add(predicate);
            if (predicate != null) {
                if (criteria.getLogicalOperator() == LogicalOperator.AND) {
                    if (resultPredicate != null) {
                        resultPredicate = cb.and(predicate, resultPredicate);
                    } else {
                        resultPredicate = predicate;
                    }
                } else {
                    if (resultPredicate != null) {
                        resultPredicate = cb.or(predicate, resultPredicate);
                    } else {
                        resultPredicate = predicate;
                    }
                }
            }
        }
        return resultPredicate;
    }

    public void AddCriteria(QueryCriteria criteria) {
        criterias.add(criteria);
        if (criteria.getOperation() == QueryOperator.JOIN_LIST) {
            joinList.add(criteria.getKey());
        }
    }

    private Path getPath(Root<T> root, String key) {
        List<String> keys = Arrays.stream(key.split(Pattern.quote("."))).collect(Collectors.toList());
        Path path = null;
        try {
            int index = 1;
            if (listJoin.containsKey(keys.get(0)) && keys.size() >= 2) {
                path = listJoin.get(keys.get(0)).get(keys.get(1));
                if (keys.size() > 2) {
                    index = 2;
                } else {
                    return path;
                }
            } else {
                path = root.get(keys.get(0));
            }

            for (int i = index; i < keys.size(); i++) {
                path = path.get(keys.get(i));
            }
        } catch (Exception exception) {
            path = null;
        }

        return path;
    }
}


package com.game.repository;

import com.game.dto.PlayerRequestParams;
import com.game.entity.Player;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlayerSpecification implements Specification<Player> {

    private final PlayerRequestParams criteria;

    public PlayerSpecification(PlayerRequestParams criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> criteriaQuery,
                                 CriteriaBuilder criteriaBuilder) {

        final List<Predicate> predicates = new ArrayList<Predicate>();


        if (criteria.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + criteria.getName() + "%"));
        }
        if (criteria.getTitle() != null) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + criteria.getTitle() + "%"));
        }
        if (criteria.getRace() != null) {
            predicates.add(criteriaBuilder.equal(root.get("race"),criteria.getRace()));
        }
        if (criteria.getProfession() != null) {
            predicates.add(criteriaBuilder.equal(root.get("profession"),criteria.getProfession()));
        }
        if (criteria.getBanned()!=null){
            predicates.add(criteriaBuilder.equal(root.get("banned"),criteria.getBanned()));
        }
        if (criteria.getAfter() != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("birthday"),new Date(criteria.getAfter())));
        }
        if (criteria.getBefore() != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("birthday"),new Date(criteria.getBefore())));
        }
        if (criteria.getMinExperience() != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("experience"),criteria.getMinExperience()));
        }
        if (criteria.getMaxExperience() != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("experience"),criteria.getMaxExperience()));
        }
        if (criteria.getMinLevel() != null) {
            predicates.add(criteriaBuilder.greaterThan(root.get("level"),criteria.getMinLevel()));
        }
        if (criteria.getMaxLevel() != null) {
            predicates.add(criteriaBuilder.lessThan(root.get("level"),criteria.getMaxLevel()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }

}


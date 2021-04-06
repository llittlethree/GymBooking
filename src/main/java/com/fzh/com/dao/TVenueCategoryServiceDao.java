package com.fzh.com.dao;

import com.fzh.com.model.TVenueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TVenueCategoryServiceDao extends JpaRepository<TVenueCategory,Long>, JpaSpecificationExecutor<TVenueCategory> {
}

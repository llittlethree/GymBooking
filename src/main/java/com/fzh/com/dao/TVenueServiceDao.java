package com.fzh.com.dao;

import com.fzh.com.model.TVenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TVenueServiceDao extends JpaSpecificationExecutor<TVenue>, JpaRepository<TVenue,Long> {
}

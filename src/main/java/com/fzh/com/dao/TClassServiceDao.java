package com.fzh.com.dao;

import com.fzh.com.model.TClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TClassServiceDao extends JpaSpecificationExecutor<TClass> , JpaRepository<TClass,Long> {
}

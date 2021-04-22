package com.fzh.com.dao;

import com.fzh.com.model.TResource;
import com.fzh.com.model.TStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TResourceDao extends JpaRepository<TResource,Long>, JpaSpecificationExecutor<TResource> {
    List<TResource> findByRefTableAndResourceCode(String table, String fileCode);

    @Modifying
    @Transactional
    @Query(value = "delete  from t_resource where id =:id",nativeQuery = true)
    Integer delById(@Param("id") Long id);
}

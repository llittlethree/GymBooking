package com.fzh.com.dao;

import com.fzh.com.model.TAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TAdminServiceDao extends JpaSpecificationExecutor<TAdmin>, JpaRepository<TAdmin,Long> {
}

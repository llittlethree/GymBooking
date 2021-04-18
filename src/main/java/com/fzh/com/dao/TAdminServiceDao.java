package com.fzh.com.dao;

import com.fzh.com.model.TAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TAdminServiceDao extends JpaSpecificationExecutor<TAdmin>, JpaRepository<TAdmin,Long> {
    @Query("from TAdmin  ta where ta.adminPhone=:phone and ta.adminPassword=:password and ta.adminStatus=:adminStatus and ta.deleteTime = 0")
    TAdmin findByAdminPhoneAndAdminPasswordAndAdminStatus(@Param("phone") String phone, @Param("password")String password,@Param("adminStatus") Integer status);
}

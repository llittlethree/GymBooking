package com.fzh.com.dao;

import com.fzh.com.model.TStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TStudentServiceDao extends JpaRepository<TStudent,Long>, JpaSpecificationExecutor<TStudent> {
    TStudent findByStudentNumberAndStudentPassword(String username, String password);

    TStudent findByStudentPhoneAndStudentPassword(String username, String password);
}

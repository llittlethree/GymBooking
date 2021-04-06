package com.fzh.com.dao;

import com.fzh.com.model.TBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TBookingServiceDao extends JpaRepository<TBooking,Long>, JpaSpecificationExecutor<TBooking> {
}

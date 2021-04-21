package com.fzh.com.dao;

import com.fzh.com.model.TBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TBookingServiceDao extends JpaRepository<TBooking,Long>, JpaSpecificationExecutor<TBooking> {
    @Query("from TBooking tb where tb.bookingStatus = ?1")
    List<TBooking> findByBookingStatus(@Param("BookingStatus") int bookingStatus);

    List<TBooking> findByBookingStatusAndBookingUserid(int bookingStatus, Long bookingUserid);

    TBooking findByNumber(String code);
}

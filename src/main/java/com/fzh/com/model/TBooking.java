package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name = "t_booking")
@Entity
public class TBooking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "number")
  private String number;

  @Column(name = "booking_userid")
  private long bookingUserid;

  @Column(name = "venue_id")
  private long venueId;

  @Column(name = "booking_start_time")
  private long bookingStartTime;

  @Column(name = "booking_end_time")
  private long bookingEndTime;

  @Column(name = "create_time")
  private long createTime;

  @Column(name = "update_time")
  private long updateTime;

  @Column(name = "delete_time")
  private long deleteTime;

  @Column(name = "booking_status")
  private long bookingStatus;

  @Column(name = "remark")
  private String remark;

}

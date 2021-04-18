package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;

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
  private Long id;

  @Column(name = "number")
  private String number;

  //一个订单对应一个用户
  @Column(name = "booking_userid")
  private Long bookingUserid;

  @OneToOne
  @JoinColumn(name = "booking_userid",referencedColumnName = "id",insertable = false,updatable = false)
  private TStudent tStudent;

  //一个订单对应一个场地
  @Column(name = "venue_id")
  private Long venueId;

  @OneToOne
  @JoinColumn(name = "venue_id",referencedColumnName = "id",insertable = false,updatable = false)
  private TVenue tVenue;

  @Column(name = "booking_start_time")
  private Long bookingStartTime;

  @Column(name = "booking_end_time")
  private Long bookingEndTime;

  @Column(name = "create_time")
  private Long createTime;

  @Column(name = "update_time")
  private Long updateTime;

  @Column(name = "delete_time")
  private Long deleteTime;

  @Column(name = "booking_status")
  private Integer bookingStatus;

  @Column(name = "remark")
  private String remark;

  @Column(name = "booking_phone")
  private String bookingPhone;

  @Column(name = "width_num")
  private Integer widthNum;

  @Column(name = "booking_student_num")
  private String bookingStudentNum;
}

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
  private long id;

  @Column(name = "number")
  private String number;

  //一个订单对应一个用户
  //@Column(name = "booking_userid")
  @OneToOne
  @JoinColumn(name = "booking_userid",referencedColumnName = "id")
  private TStudent tStudent;

  //一个订单对应一个场地
  //@Column(name = "venue_id")
  @OneToOne
  @JoinColumn(name = "venue_id",referencedColumnName = "id")
  private TVenue tVenue;

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
  private Integer bookingStatus;

  @Column(name = "remark")
  private String remark;

  @Column(name = "booking_phone")
  private String bookingPhone;

  @Column(name = "width_num")
  private Integer widthNum;

  @Column(name = "booking_student_num")
  private Integer bookingStudentNum;
}

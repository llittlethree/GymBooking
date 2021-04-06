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
@Table(name = "t_student")
@Entity
public class TStudent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "openid")
  private String openid;

  @Column(name = "student_number")
  private String studentNumber;

  @Column(name = "student_phone")
  private String studentPhone;

  @Column(name = "student_password")
  private String studentPassword;

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "student_sex")
  private long studentSex;

  @Column(name = "class_id")
  private long classId;

  @Column(name = "create_time")
  private long createTime;

  @Column(name = "update_time")
  private long updateTime;

  @Column(name = "delete_time")
  private long deleteTime;

  @Column(name = "student_status")
  private long studentStatus;

  @Column(name = "remark")
  private String remark;


}

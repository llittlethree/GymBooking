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
  private Long id;

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
  private Long studentSex;

  @Column(name = "class_id")
  private Long classId;

  //一对一 一个学生对应一个班级
  @OneToOne
  @JoinColumn(name = "class_id",referencedColumnName = "id",insertable = false,updatable = false)
  private TClass tClass;

  @Column(name = "create_time")
  private Long createTime;

  @Column(name = "update_time")
  private Long updateTime;

  @Column(name = "delete_time")
  private Long deleteTime;

  @Column(name = "student_status")
  private Long studentStatus;

  @Column(name = "remark")
  private String remark;


}

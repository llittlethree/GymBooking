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
@Table(name = "t_admin")
@Entity
public class TAdmin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "admin_phone")
  private String adminPhone;

  @Column(name = "admin_name")
  private String adminName;

  @Column(name = "admin_password")
  private String adminPassword;

  @Column(name = "admin_sex")
  private long adminSex;

  @Column(name = "create_time")
  private long createTime;

  @Column(name = "update_time")
  private long updateTime;

  @Column(name = "delete_time")
  private long deleteTime;

  @Column(name = "admin_status")
  private long adminStatus;

  @Column(name = "remark")
  private String remark;

}

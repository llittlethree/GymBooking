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
  private Long  id;

  @Column(name = "admin_phone")
  private String adminPhone;

  @Column(name = "admin_name")
  private String adminName;

  @Column(name = "admin_password")
  private String adminPassword;

  @Column(name = "admin_sex")
  private Integer adminSex;

  @Column(name = "create_time")
  private Long createTime;

  @Column(name = "update_time")
  private Long updateTime;

  @Column(name = "delete_time")
  private Long deleteTime;

  @Column(name = "admin_status")
  private Integer adminStatus;

  @Column(name = "remark")
  private String remark;

}

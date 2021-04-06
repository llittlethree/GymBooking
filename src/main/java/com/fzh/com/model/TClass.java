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
@Table(name = "t_class")
@Entity
public class TClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "class_name")
  private String className;

  @Column(name = "college_name")
  private String collegeName;

  @Column(name = "professional_name")
  private String professionalName;

  @Column(name = "create_time")
  private long createTime;

  @Column(name = "update_time")
  private long updateTime;

  @Column(name = "delete_time")
  private long deleteTime;

  @Column(name = "class_status")
  private long classStatus;

  @Column(name = "remark")
  private String remark;


}

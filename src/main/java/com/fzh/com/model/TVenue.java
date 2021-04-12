package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Table(name = "t_venue")
@Entity
public class TVenue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  //一对一 一个场地对应一个场地类型
  @Column(name = "venue_category_id")
  private Long venueCategoryId;

  @OneToOne
  @JoinColumn(name = "venue_category_id",referencedColumnName = "id")
  private TVenueCategory tVenueCategory;


  @Column(name = "venue_name")
  private String venueName;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "create_time")
  private long createTime;

  @Column(name = "update_time")
  private long updateTime;

  @Column(name = "delete_time")
  private long deleteTime;

  @Column(name = "remark")
  private String remark;

  @Column(name = "max_use")
  private long maxUse;

  //一对多，一个场地对应多个资源
  @OneToMany(targetEntity = TResource.class)
  @JoinColumn(name = "venue_resource_id",referencedColumnName = "id")
  private Set<TResource> tResourceSet = new HashSet<TResource>();


}

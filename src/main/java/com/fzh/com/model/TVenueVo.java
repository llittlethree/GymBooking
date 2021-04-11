package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author 张小三
 * @create 2021-04-11 23:48
 * @verson 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class TVenueVo {
    private long id;
    private TVenueCategory tVenueCategory;
    private String venueName;
    private BigDecimal price;
    private long createTime;
    private long updateTime;
    private long deleteTime;
    private String remark;
    private long maxUse;


}

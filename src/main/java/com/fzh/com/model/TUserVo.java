package com.fzh.com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 张小三
 * @create 2021-04-10 17:55
 * @verson 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class TUserVo {

    private long id;


    private String openid;


    private String username;


    private String phone;

    private String token;


    private String name;


    private long sex;

    private long classId;


    private long createTime;

    private long updateTime;

    private long deleteTime;

    private long status;
    private String studentNumber;

    private String remark;
    private Integer sign;//0学生，1管理
}

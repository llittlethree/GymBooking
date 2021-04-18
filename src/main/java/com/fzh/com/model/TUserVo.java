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

    private Long id;


    private String openid;


    private String username;


    private String phone;

    private String token;


    private String name;


    private Long sex;

    private Long classId;


    private Long createTime;

    private Long updateTime;

    private Long deleteTime;

    private Long status;
    private String studentNumber;

    private String remark;
    private Integer sign;//0学生，1管理
}

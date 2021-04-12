package com.fzh.com.sevice;

import com.fzh.com.model.TVenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

/**
 * @author 张小三
 * @create 2021-03-24 22:52
 * @verson 1.0.0
 */
public interface TVenueService {
    /**
     * 说明: 分页查找场地列表
     *
     * @param pageable        分页
     * @param venueCategoryId Integer 场地类别id -1代表全部（默认-1）
     * @param venueName       String  场地名称
     * @param price           BigDecimal  场地价格
     * @param maxUse          Integer 最大使用量
     * @return
     * @author zhangxiaosan
     * @create 2021/3/25
     */
    Page<TVenue> list(Pageable pageable, Integer venueCategoryId, String venueName, BigDecimal price, Integer maxUse) throws Exception;

    /**
     * 说明: 根据id查找详情
     * @author   zhangxiaosan
     * @create   2021/4/1
     * @param  id Integer 场地id
     * @return
     */
    TVenue findById(Integer id) throws Exception;

    /**
     * 说明: 根据id查找详情
     * @author   zhangxiaosan
     * @create   2021/4/1
     * @param  id Integer 场地id
     * @return
     */
    TVenue findById(Long id) throws Exception;
    /**
     * 添加场地
     * @param tVenue TVenue 场地实体
     * @return 返回添加成功的场地实体对象
     * */
    TVenue save(TVenue tVenue) throws Exception;
}

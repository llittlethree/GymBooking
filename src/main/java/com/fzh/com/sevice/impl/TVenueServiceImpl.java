package com.fzh.com.sevice.impl;

import com.fzh.com.model.TBooking;
import com.fzh.com.model.TVenue;
import com.fzh.com.sevice.TVenueService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.PageUtil;
import com.fzh.com.utils.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.fzh.com.dao.TVenueServiceDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 张小三
 * @create 2021-03-24 22:55
 * @verson 1.0.0
 */
@Service
public class TVenueServiceImpl implements TVenueService {
    @Autowired
    private TVenueServiceDao tVenueServiceDao;


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
    @Override
    public Page<TVenue> list(Pageable pageable, Integer venueCategoryId, String venueName, BigDecimal price, Integer maxUse) throws Exception {
        return tVenueServiceDao.findAll(new Specification<TVenue>() {
            @Override
            public Predicate toPredicate(Root<TVenue> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if (venueCategoryId != null && venueCategoryId > 0) {
                    predicateList.add(cb.equal(root.get("venueCategoryId"), venueCategoryId));
                }

                if (StringUtil.isNoEmpty(venueName)) {
                    predicateList.add(cb.like(root.get("venueName").as(String.class), "%" + venueName + "%"));
                }

                if (price != null) {
                    predicateList.add(cb.equal(root.get("price"), price));
                }

                if (maxUse != null && maxUse > 0) {
                    predicateList.add(cb.equal(root.get("maxUse"), maxUse));
                }

                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        },pageable);
    }

    /**
     * 说明: 根据id查找详情
     *
     * @param id Integer 场地id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/1
     */
    @Override
    public TVenue findById(Integer id) throws Exception {
        Long idL = Long.valueOf(id.toString());
        return tVenueServiceDao.findById(idL).get();
    }

    /**
     * 说明: 根据id查找详情
     *
     * @param id Integer 场地id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/1
     */
    @Override
    public TVenue findById(Long id) throws Exception {
        Long idL = Long.valueOf(id.toString());
        return tVenueServiceDao.findById(idL).get();
    }

    /**
     * 添加场地
     *
     * @param tVenue TVenue 场地实体
     * @return 返回添加成功的场地实体对象
     */
    @Override
    public TVenue save(TVenue tVenue) throws Exception {
        return tVenueServiceDao.save(tVenue);
    }

    /***
     * 查找 场地列表分页
     * @param pageable 分页
     * @param venueCategoryId 类型id
     * @param venueName 类型名称
     * @param start 创建开始时间
     * @param end 创建结束时间
     * @param price 价格
     * @param maxUse 最大使用量
     * @return
     * @throws Exception
     */
    @Override
    public Page list(Pageable pageable, String venueCategoryId, String venueName, String start, String end, String price, String maxUse) throws Exception {
        return tVenueServiceDao.findAll(new Specification<TVenue>() {
            @Override
            public Predicate toPredicate(Root<TVenue> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(StringUtil.isNoEmpty(venueName)){
                    predicateList.add(cb.like(root.get("venueName"), "%"+venueName+"%"));
                }
                if(StringUtil.isNoEmpty(venueCategoryId)){
                    predicateList.add(cb.equal(root.get("venueCategoryId"), venueCategoryId));
                }
                if(StringUtil.isNoEmpty(maxUse)){
                    predicateList.add(cb.equal(root.get("maxUse"), maxUse));
                }
                if(StringUtil.isNoEmpty(price)){
                    predicateList.add(cb.equal(root.get("price"), price));
                }
                if(StringUtil.isNoEmpty(start)){
                    predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), start));
                }
                if(StringUtil.isNoEmpty(end)){
                    predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), end));
                }

                predicateList.add(cb.lessThanOrEqualTo(root.get("deleteTime"),0 ));
                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        },pageable) ;
    }

    /**
     * 说明: 删除一条记录
     *
     * @param id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/16
     */
    @Override
    public int deleteOne(Integer id) throws Exception {
        TVenue one = tVenueServiceDao.getOne(Long.valueOf(id));
        one.setDeleteTime(Long.valueOf(DateUtil.getTimeStampNow()));
        TVenue save = tVenueServiceDao.save(one);
        if (save!=null)return 1;
        return 0;
    }
}

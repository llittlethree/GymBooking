package com.fzh.com.sevice.impl;
import com.fzh.com.dao.TVenueCategoryServiceDao;
import com.fzh.com.model.TVenue;
import com.fzh.com.model.TVenueCategory;
import com.fzh.com.sevice.TVenueCategoryService;
import com.fzh.com.utils.DateUtil;
import com.fzh.com.utils.ResponseUtil;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张小三
 * @create 2021-03-24 23:17
 * @verson 1.0.0
 */
@Service
public class TVenueCategoryServiceImpl implements TVenueCategoryService {
    @Autowired
    private TVenueCategoryServiceDao tVenueCategoryServiceDao;

    /**
     * 说明: 获取所有场地类型列表 （不分页）
     *
     * @param venueCategoryName String 场地名称
     * @return 场地列表 List<TVenueCategory>
     * @author zhangxiaosan
     * @create 2021/3/24
     */
    @Override
    public List<TVenueCategory> findAll(String venueCategoryName) throws Exception {
        return tVenueCategoryServiceDao.findAll(new Specification<TVenueCategory>() {
            @Override
            public Predicate toPredicate(Root<TVenueCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(StringUtil.isNoEmpty(venueCategoryName)){
                    predicateList.add(cb.like(root.get("venueCategoryName").as(String.class),"%"+venueCategoryName+"%"));
                }

                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        });
    }

    /**
     * 说明: 场地类型
     *
     * @param pageable
     * @param venueCategoryName 类型名称
     * @return
     * @author zhangxiaosan
     * @create 2021/4/20
     */
    @Override
    public Page list(Pageable pageable, String venueCategoryName) throws Exception {
        return tVenueCategoryServiceDao.findAll(new Specification<TVenueCategory>() {
            @Override
            public Predicate toPredicate(Root<TVenueCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(StringUtil.isNoEmpty(venueCategoryName)){
                    predicateList.add(cb.like(root.get("venueCategoryName").as(String.class),"%"+venueCategoryName+"%"));
                }

                Predicate[] pre = new Predicate[predicateList.size()];
                criteriaQuery.where(predicateList.toArray(pre));
                return cb.and(predicateList.toArray(pre));
            }
        },pageable);
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
    public Integer deleteOne(Integer id) throws Exception {
        TVenueCategory one = tVenueCategoryServiceDao.getOne(Long.valueOf(id));
        if (one == null) return 0;
        tVenueCategoryServiceDao.delete(one);
        return 1;
    }

    /**
     * 说明: 添加类型
     *
     * @param tVenueCategory
     * @return
     * @author zhangxiaosan
     * @create 2021/4/20
     */
    @Override
    public TVenueCategory save(TVenueCategory tVenueCategory) throws Exception {
        return tVenueCategoryServiceDao.save(tVenueCategory);
    }

    /**
     * 说明: 根绝id查找类型
     *
     * @param id
     * @return
     * @author zhangxiaosan
     * @create 2021/4/20
     */
    @Override
    public TVenueCategory getByid(String id) throws Exception {
        return tVenueCategoryServiceDao.findById(Long.valueOf(id)).get();
    }


}

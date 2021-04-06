package com.fzh.com.sevice.impl;
import com.fzh.com.dao.TVenueCategoryServiceDao;
import com.fzh.com.model.TVenueCategory;
import com.fzh.com.sevice.TVenueCategoryService;
import com.fzh.com.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
}

package com.fzh.com.utils;

import com.fzh.com.model.TVenue;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * jpa 分页工具封装
 * @author 张小三
 * @create 2021-03-25 23:05
 * @verson 1.0.0
 */
public class PageUtil {
    private  static Map map = new HashMap();

    //初始化
    static{
        map.put("total",0);//总记录数
        map.put("datas",new ArrayList());//数据
        map.put("page",1);//当前页码
        map.put("pageSize",10);//当前页显示数量
        map.put("pageTotal",1);//总页数
    }


    /**
    * 说明: 构建分页排序方法和页码页数
    * @author   zhangxiaosan
    * @create   2021/3/26
    * @param
    * @return
    */
    public static Pageable page(Integer page,Integer pageSize,String order, String  orderBy){
        /*
        * 排序方式和排序字段对应，兼容多个字段不同的排序方式，
        * 例如：根据id倒叙，且根据价格顺序
        *      order: desc,asc
        *      orderBy: id,price
        * */


        //多个排序字段
        String[] orderList = order.split(",");
        for (String _order:orderList){
            if (!"desc".equalsIgnoreCase(_order) && !"asc".equalsIgnoreCase(_order)){
                System.out.println("排序方式不正确："+_order);
                return null;
            }
        }
        //有多个排序方式
        String[] orderByList = orderBy.split(",");

        //构造排序
        ArrayList<Sort.Order> orders = new ArrayList<>();
        int i=0;
        for (String getOrderBy:orderByList) {
            String orderListItem = orderList[0];
            if(orderByList.length == orderList.length){
                orderListItem = orderList[i];
            }
            if ("desc".equalsIgnoreCase(orderListItem)) {
                orders.add(new Sort.Order(Sort.Direction.DESC, getOrderBy));
            } else {
                orders.add(new Sort.Order(Sort.Direction.ASC, getOrderBy));
            }
            i++;
        }


        Pageable pageable= PageRequest.of(page-1, pageSize,Sort.by(orders));// new Sort(orders)
        return pageable;
    }

    /**
    * 说明: 查询到的数据从分页中取出并转换成对应的格式
    * @author   zhangxiaosan
    * @create   2021/3/26
    * @param
    * @param page
     * @return
    */
    public static Map pageFormart(Page<T> page){
        long totalElements = page.getTotalElements();
        List<T> content = page.getContent();
        map.put("total",totalElements);//总记录数
        map.put("datas",content);//数据
        map.put("page",page.getPageable().getPageNumber());//当前页码
        map.put("pageSize",page.getPageable().getPageSize());//当前页显示数量
        map.put("pageTotal",page.getTotalPages());//总页数
        return map;
    }

    public static void main(String[] args) {
        Integer page=1;
        Integer pageSize=10;
        String order="desc";
        String  orderBy ="id";
       // Pageable page1 = page(page, pageSize, order, orderBy);
       // Pageable page1 =  PageRequest.of(page, pageSize, Sort.Direction.DESC, orderBy);
        Pageable page1 = page(page, pageSize,"desc", orderBy);
        System.out.println(page1);

        ArrayList<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        /*orders.add(new Sort.Order(Sort.Direction.DESC, "price"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "name"));*/
        Pageable pageable= PageRequest.of(page, pageSize,Sort.by(orders));// new Sort(orders)
        System.out.println(pageable);
    }

}

package com.page.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.page.dao.IOrdersDao;
import com.page.domain.Orders;
import com.page.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    /*@Override
    public List<Orders> findAll(int page, int size) throws Exception {

        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }*/

    @Override
    public PageInfo findAll(int page, int size) throws Exception {

        //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page, size);
        List<Orders> ordersList = ordersDao.findAll();

        //PageInfo就是一个分页Bean
        PageInfo pageInfo=new PageInfo(ordersList);

        //设置每页可以展示数据的条数
        pageInfo.setNavigatepageNums(new int[]{1, 2, 3, 4, 5, 6});

        //导航条上的第一页
        int firstPage;
        //导航条上的最后一页
        int lastPage;

        int pages = pageInfo.getPages(); // 获取总页数
        int pageNum = pageInfo.getPageNum(); // 当前的页码
        if (pages <= 10) {
            firstPage = 1;
            lastPage = pages;
        } else {
            firstPage = pageNum - 5;
            lastPage = pageNum + 4;

            // 如果导航条上的第一页小于1
            if (firstPage < 1) {
                firstPage = 1;
                lastPage = 10;
            }

            // 导航条上的最后一页大于总页数
            if (lastPage > pages) {
                firstPage = pages - 9;
                lastPage = pages;
            }
        }

        pageInfo.setNavigateFirstPage(firstPage);
        pageInfo.setNavigateLastPage(lastPage);

        return pageInfo;
    }
}

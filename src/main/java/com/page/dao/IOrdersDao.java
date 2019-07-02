package com.page.dao;


import com.page.domain.Orders;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrdersDao {

    @Select("select * from orders")
    List<Orders> findAll() throws Exception;
}

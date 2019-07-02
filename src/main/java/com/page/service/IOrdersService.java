package com.page.service;

import com.github.pagehelper.PageInfo;
import com.page.domain.Orders;

import java.util.List;

public interface IOrdersService {


    PageInfo findAll(int page, int size) throws Exception;
}

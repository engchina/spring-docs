package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.BaseTable;

import java.util.List;
import java.util.Map;

/**
 * author explorer
 */
public interface BaseTableService {

    BaseTable selectOne(Map<String, Object> paramMap);

    List<BaseTable> selectAll(Map<String, Object> paramMap);

    Integer insertOne(Map<String, Object> paramMap);

    Integer updateOne(Map<String, Object> paramMap);

    Integer deleteOne(Map<String, Object> paramMap);
}

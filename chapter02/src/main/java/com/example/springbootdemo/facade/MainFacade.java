package com.example.springbootdemo.facade;

import com.example.springbootdemo.entity.BaseTable;
import com.example.springbootdemo.entity.TableInfo;

import java.util.List;
import java.util.Map;

public interface MainFacade {

    List<TableInfo> selectTableInfo(Map<String, Object> paramMap);

    BaseTable selectOne(Map<String, Object> paramMap);

    List<BaseTable> selectAll(Map<String, Object> paramMap);

    Integer insertOne(Map<String, Object> paramMap);

    Integer updateOne(Map<String, Object> paramMap);

    Integer deleteOne(Map<String, Object> paramMap);
}

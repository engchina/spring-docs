package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.TableInfo;

import java.util.List;
import java.util.Map;

/**
 * author explorer
 */
public interface TableInfoMapper {

    List<TableInfo> selectTableInfo(Map<String, Object> paramMap);
}

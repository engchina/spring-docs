package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.TableInfo;

import java.util.List;
import java.util.Map;

public interface TableInfoService {

        List<TableInfo> selectTableInfo(Map<String, Object> paramMap);
}

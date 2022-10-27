package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.TableInfo;
import com.example.springbootdemo.mapper.TableInfoMapper;
import com.example.springbootdemo.service.TableInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Resource
    TableInfoMapper tableInfoMapper;

    @Override
    public List<TableInfo> selectTableInfo(Map<String, Object> paramMap) {
        return tableInfoMapper.selectTableInfo(paramMap);
    }
}

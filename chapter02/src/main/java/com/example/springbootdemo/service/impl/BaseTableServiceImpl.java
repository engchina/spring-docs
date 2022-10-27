package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.entity.BaseTable;
import com.example.springbootdemo.mapper.BaseTableMapper;
import com.example.springbootdemo.service.BaseTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * author explorer
 */
@Service
public class BaseTableServiceImpl implements BaseTableService {

    @Resource
    BaseTableMapper baseTableMapper;

    @Override
    public BaseTable selectOne(Map<String, Object> paramMap) {
        return baseTableMapper.selectOne(paramMap);
    }

    public List<BaseTable> selectAll(Map<String, Object> paramMap){
        return baseTableMapper.selectAll(paramMap);
    }

    @Override
    public Integer insertOne(Map<String, Object> paramMap) {
        return baseTableMapper.insertOne(paramMap);
    }

    @Override
    public Integer updateOne(Map<String, Object> paramMap) {
        return baseTableMapper.updateOne(paramMap);
    }

    @Override
    public Integer deleteOne(Map<String, Object> paramMap) {
        return baseTableMapper.deleteOne(paramMap);
    }
}

package com.example.springbootdemo.facade.impl;

import com.example.springbootdemo.entity.BaseTable;
import com.example.springbootdemo.entity.TableInfo;
import com.example.springbootdemo.facade.MainFacade;
import com.example.springbootdemo.service.BaseTableService;
import com.example.springbootdemo.service.TableInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MainFacadeImpl implements MainFacade {

    @Resource
    TableInfoService tableInfoService;

    @Resource
    BaseTableService baseTableService;

    @Override
    public List<TableInfo> selectTableInfo(Map<String, Object> paramMap) {
        return tableInfoService.selectTableInfo(paramMap);
    }

    @Override
    public BaseTable selectOne(Map<String, Object> paramMap) {
        return baseTableService.selectOne(paramMap);
    }

    @Override
    public List<BaseTable> selectAll(Map<String, Object> paramMap) {
        return baseTableService.selectAll(paramMap);
    }

    @Override
    public Integer insertOne(Map<String, Object> paramMap) {
        return baseTableService.insertOne(paramMap);
    }

    @Override
    public Integer updateOne(Map<String, Object> paramMap) {
        return baseTableService.updateOne(paramMap);
    }

    @Override
    public Integer deleteOne(Map<String, Object> paramMap) {
        return baseTableService.deleteOne(paramMap);
    }
}

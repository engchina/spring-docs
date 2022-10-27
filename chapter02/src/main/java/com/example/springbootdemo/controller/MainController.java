package com.example.springbootdemo.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.springbootdemo.entity.BaseTable;
import com.example.springbootdemo.entity.TableInfo;
import com.example.springbootdemo.facade.MainFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MainController {

    @Resource
    MainFacade mainFacade;

    @RequestMapping("/health")
    public String sayHello() {
        return "UP!";
    }

    @RequestMapping("/rest/{tableSchema}/{tableName}/{id}")
    public JSONObject selectOne(@PathVariable("tableSchema") String tableSchema, @PathVariable("tableName") String tableName, @PathVariable("id") String id) throws NoSuchFieldException, IllegalAccessException {
        // get tableinfo
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableSchema", tableSchema);
        paramMap.put("tableName", tableName);
        List<TableInfo> tableInfos = mainFacade.selectTableInfo(paramMap);
        log.info("tableInfos:" + tableInfos);
        int varcharCount = 1;
        int intCount = 1;
        for (int i = 0; i < tableInfos.size(); i++) {
            TableInfo tableInfo = tableInfos.get(i);
            String columnType = tableInfo.getColumnType();
            if (columnType.contains("varchar")) {
                paramMap.put(Strings.concat("varchar", String.valueOf(varcharCount)), tableInfo.getColumnName());
                varcharCount++;
            } else if (columnType.contains("int")) {
                paramMap.put(Strings.concat("int", String.valueOf(intCount)), tableInfo.getColumnName());
                intCount++;
            }
        }

        // get data
        paramMap.put("id", id);
        BaseTable baseTable = mainFacade.selectOne(paramMap);
        log.info("baseTable:" + baseTable);
        paramMap.remove("tableSchema");
        paramMap.remove("tableName");
        paramMap.remove("id");

        Class<? extends BaseTable> baseTableClass = baseTable.getClass();
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String fieldName = entry.getKey();
            Object key = entry.getValue();
            Field field = baseTableClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            Object oValue = field.get(baseTable);
            jsonObject.put((String) key, oValue);
        }

        return jsonObject;
    }

    @RequestMapping("/rest/{tableSchema}/{tableName}")
    public List<JSONObject> selectAll(@PathVariable("tableSchema") String tableSchema, @PathVariable("tableName") String tableName) throws NoSuchFieldException, IllegalAccessException {
        // get tableinfo
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableSchema", tableSchema);
        paramMap.put("tableName", tableName);
        List<TableInfo> tableInfos = mainFacade.selectTableInfo(paramMap);
        log.info("tableInfos:" + tableInfos);
        int varcharCount = 1;
        int intCount = 1;
        for (int i = 0; i < tableInfos.size(); i++) {
            TableInfo tableInfo = tableInfos.get(i);
            String columnType = tableInfo.getColumnType();
            if (columnType.contains("varchar")) {
                paramMap.put(Strings.concat("varchar", String.valueOf(varcharCount)), tableInfo.getColumnName());
                varcharCount++;
            } else if (columnType.contains("int")) {
                paramMap.put(Strings.concat("int", String.valueOf(intCount)), tableInfo.getColumnName());
                intCount++;
            }
        }

        // get data
        List<BaseTable> baseTableList = mainFacade.selectAll(paramMap);
        log.info("baseTableList:" + baseTableList);
        paramMap.remove("tableSchema");
        paramMap.remove("tableName");

        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int i = 0; i < baseTableList.size(); i++) {
            BaseTable baseTable = baseTableList.get(i);
            Class<? extends BaseTable> baseTableClass = baseTable.getClass();
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                String fieldName = entry.getKey();
                Object key = entry.getValue();
                Field field = baseTableClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object oValue = field.get(baseTable);
                jsonObject.put((String) key, oValue);
            }
            jsonObjectList.add(jsonObject);
        }

        return jsonObjectList;
    }


    @PostMapping("/rest/{tableSchema}/{tableName}")
    public JSONObject insertOne(@PathVariable("tableSchema") String tableSchema, @PathVariable("tableName") String tableName, @RequestBody JSONObject jsonObject) {
        // get tableinfo
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableSchema", tableSchema);
        paramMap.put("tableName", tableName);
        List<TableInfo> tableInfos = mainFacade.selectTableInfo(paramMap);
        log.info("tableInfos:" + tableInfos);
        int varcharCount = 1;
        int intCount = 1;
        for (int i = 0; i < tableInfos.size(); i++) {
            TableInfo tableInfo = tableInfos.get(i);
            String columnType = tableInfo.getColumnType();
            if (jsonObject.get(tableInfo.getColumnName()) != null) {
                if (columnType.contains("varchar")) {
                    paramMap.put(Strings.concat("varchar", String.valueOf(varcharCount)), tableInfo.getColumnName());
                    paramMap.put(Strings.concat(Strings.concat("varchar", String.valueOf(varcharCount)), "value"), jsonObject.get(tableInfo.getColumnName()));
                    varcharCount++;
                } else if (columnType.contains("int")) {
                    paramMap.put(Strings.concat("int", String.valueOf(intCount)), tableInfo.getColumnName());
                    paramMap.put(Strings.concat(Strings.concat("int", String.valueOf(intCount)), "value"), jsonObject.get(tableInfo.getColumnName()));
                    intCount++;
                }
            }
        }

        Integer insertOneResult = mainFacade.insertOne(paramMap);
        log.info("insertOneResult:" + insertOneResult);

        return jsonObject;
    }

    @PutMapping("/rest/{tableSchema}/{tableName}/{id}")
    public JSONObject updateOne(@PathVariable("tableSchema") String tableSchema, @PathVariable("tableName") String tableName, @PathVariable("id") String id, @RequestBody JSONObject jsonObject) {
        // get tableinfo
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableSchema", tableSchema);
        paramMap.put("tableName", tableName);
        List<TableInfo> tableInfos = mainFacade.selectTableInfo(paramMap);
        log.info("tableInfos:" + tableInfos);
        int varcharCount = 1;
        int intCount = 1;
        for (int i = 0; i < tableInfos.size(); i++) {
            TableInfo tableInfo = tableInfos.get(i);
            String columnType = tableInfo.getColumnType();
            if (jsonObject.get(tableInfo.getColumnName()) != null) {
                if (columnType.contains("varchar")) {
                    paramMap.put(Strings.concat("varchar", String.valueOf(varcharCount)), tableInfo.getColumnName());
                    paramMap.put(Strings.concat(Strings.concat("varchar", String.valueOf(varcharCount)), "value"), jsonObject.get(tableInfo.getColumnName()));
                    varcharCount++;
                } else if (columnType.contains("int")) {
                    paramMap.put(Strings.concat("int", String.valueOf(intCount)), tableInfo.getColumnName());
                    paramMap.put(Strings.concat(Strings.concat("int", String.valueOf(intCount)), "value"), jsonObject.get(tableInfo.getColumnName()));
                    intCount++;
                }
            }
        }

        paramMap.put("id", id);
        Integer updateOneResult = mainFacade.updateOne(paramMap);
        log.info("updateOneResult:" + updateOneResult);

        return jsonObject;
    }

    @DeleteMapping("/rest/{tableSchema}/{tableName}/{id}")
    public JSONObject deleteOne(@PathVariable("tableSchema") String tableSchema, @PathVariable("tableName") String tableName, @PathVariable("id") String id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("tableName", tableName);
        paramMap.put("id", id);
        Integer deleteOneResult = mainFacade.deleteOne(paramMap);
        log.info("deleteOneResult:" + deleteOneResult);

        return new JSONObject();
    }

}

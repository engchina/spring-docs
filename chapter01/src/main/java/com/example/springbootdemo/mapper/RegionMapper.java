package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RegionMapper {

    @Select("select region_id, region_name from region")
    @Results(id = "region",
            value = {
                    @Result(property = "regionId", column = "region_id"),
                    @Result(property = "regionName", column = "region_name")})
    List<Region> selectAll();
}

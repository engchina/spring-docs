package com.oracle.springboot.mapper;

import com.oracle.springboot.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegionMapper {

    List<Region> selectRegionList();

    Region selectRegionByRegionId(@Param("regionId") String regionId);

    int insertRegion(Region region);

    int updateRegionByRegionId(Region region);

    int deleteRegionByRegionId(@Param("regionId") String regionId);
}

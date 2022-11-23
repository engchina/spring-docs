package com.oracle.springboot.service;

import com.oracle.springboot.entity.Region;

import java.util.List;

public interface MainService {

    List<Region> selectRegionList();

    Region selectRegionByRegionId(String regionId);

    int insertRegion(Region region);

    int updateRegionByRegionId(Region region);

    int deleteRegionByRegionId(String regionId);

}

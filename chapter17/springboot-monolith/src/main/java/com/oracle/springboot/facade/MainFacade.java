package com.oracle.springboot.facade;

import com.oracle.springboot.entity.Region;

import java.util.List;

public interface MainFacade {

    List<Region> selectRegionList();

    Region selectRegionByRegionId(String regionId);

    int insertRegion(Region region);

    int updateRegionByRegionId(Region region);

    int deleteRegionByRegionId(String regionId);

    void dummyMethod1();
    void dummyMethod2();
}

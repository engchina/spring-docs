package com.oracle.springbootsession.facade;

import com.oracle.springbootsession.entity.Region;

import java.util.List;

public interface MainFacade {

    List<Region> selectRegionList();

    Region selectRegionByRegionId(String regionId);

    int insertRegion(Region region);

    int updateRegionByRegionId(Region region);

    int deleteRegionByRegionId(String regionId);
}

package com.oracle.springbootsession.facade.impl;

import com.oracle.springbootsession.entity.Region;
import com.oracle.springbootsession.facade.MainFacade;
import com.oracle.springbootsession.service.MainService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
public class MainFacadeImpl implements MainFacade {

    @Resource
    MainService mainService;

    @Override
    public List<Region> selectRegionList() {
        return mainService.selectRegionList();
    }

    @Override
    public Region selectRegionByRegionId(String regionId) {
        return mainService.selectRegionByRegionId(regionId);
    }

    @Override
    public int insertRegion(Region region) {
        return mainService.insertRegion(region);
    }

    @Override
    public int updateRegionByRegionId(Region region) {
        return mainService.updateRegionByRegionId(region);
    }

    @Override
    public int deleteRegionByRegionId(String regionId) {
        return mainService.deleteRegionByRegionId(regionId);
    }
}

package com.oracle.springboot.service.impl;

import com.oracle.springboot.entity.Region;
import com.oracle.springboot.mapper.RegionMapper;
import com.oracle.springboot.service.MainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MainServiceImpl implements MainService {

    @Resource
    RegionMapper regionMapper;

    @Override
    public List<Region> selectRegionList() {
        return regionMapper.selectRegionList();
    }

    @Override
    public Region selectRegionByRegionId(String regionId) {
        return regionMapper.selectRegionByRegionId(regionId);
    }

    @Override
    public int insertRegion(Region region) {
        return regionMapper.insertRegion(region);
    }

    @Override
    public int updateRegionByRegionId(Region region) {
        return regionMapper.updateRegionByRegionId(region);
    }

    @Override
    public int deleteRegionByRegionId(String regionId) {
        return regionMapper.deleteRegionByRegionId(regionId);
    }
}

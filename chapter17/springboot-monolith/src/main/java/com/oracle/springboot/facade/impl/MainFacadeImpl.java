package com.oracle.springboot.facade.impl;

import com.oracle.springboot.annotation.MethodSpan;
import com.oracle.springboot.entity.Region;
import com.oracle.springboot.facade.MainFacade;
import com.oracle.springboot.service.MainService;
import io.opentracing.contrib.spring.cloud.aop.Traced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional
@Slf4j
public class MainFacadeImpl implements MainFacade {

    @Resource
    MainService mainService;

    @Override
    public List<Region> selectRegionList() {
        MainFacade mainFacade = (MainFacade)AopContext.currentProxy();
        mainFacade.dummyMethod1();
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

    @Override
    @Traced
    public void dummyMethod1() {
        try {
            Thread.sleep(1);
            MainFacade mainFacade = (MainFacade)AopContext.currentProxy();
            mainFacade.dummyMethod2();
            log.info("### in MainFacadeImpl.dummyMethod1() ###");
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    @Override
    @Traced
    public void dummyMethod2() {
        try {
            Thread.sleep(2);
            log.info("### in MainFacadeImpl.dummyMethod2() ###");
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}

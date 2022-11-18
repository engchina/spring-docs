package com.oracle.springbootsession.controller;

import com.oracle.springbootsession.entity.Region;
import com.oracle.springbootsession.facade.MainFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class ApiController {

    @Resource
    MainFacade mainFacade;

    @GetMapping(value = "/health")
    public Map<String, String> health() {
        Map<String, String> returnMap = new HashMap<>();
        returnMap.put("STATUS", "UP");
        return returnMap;
    }

    @GetMapping(value = "/regions")
    public List<Region> selectRegionList() {

        return mainFacade.selectRegionList();
    }

    @GetMapping(value = "/regions/{regionId}")
    public Region selectRegionByRegionId(@PathVariable("regionId") String regionId) {
        System.out.println(regionId);
        return mainFacade.selectRegionByRegionId(regionId);
    }

    @PostMapping(value = "/regions")
    public int insertRegion(@RequestBody Region region) {
        return mainFacade.insertRegion(region);
    }

    @PostMapping(value = "/regions/{regionId}")
    public int updateRegionByRegionId(@PathVariable("regionId") String regionId, @RequestBody Region region) {
        region.setRegionId(regionId);
        return mainFacade.updateRegionByRegionId(region);
    }

    @DeleteMapping(value = "/regions/{regionId}")
    public int deleteRegionByRegionId(@PathVariable("regionId") String regionId) {
        return mainFacade.deleteRegionByRegionId(regionId);
    }

}

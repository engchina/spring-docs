package com.oracle.springboot.controller;

import com.oracle.springboot.entity.Region;
import com.oracle.springboot.facade.MainFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/main")
@Slf4j
public class MainController {

    @Resource
    MainFacade mainFacade;

    @GetMapping(value = "/regions/goList")
    public String selectRegionList(Model model) {
        List<Region> regions = mainFacade.selectRegionList();
        model.addAttribute("regions", regions);
        return "regions/list";
    }

    private String method3(){
        log.info("###in MainController-method3()###");
        return "method3";
    }

    @GetMapping(value = "/regions/goInsert")
    public String goInsertRegion(Model model, Region region) {
        log.info("###in MainController-goInsertRegion()###");
        return "regions/insert";
    }

    @GetMapping(value = "/regions/goUpdate/{regionId}")
    public String goUpdateByRegionId(Model model, @PathVariable("regionId") String regionId) {
        Region region = mainFacade.selectRegionByRegionId(regionId);
        model.addAttribute("region", region);
        return "regions/update";
    }

    @PostMapping(value = "/regions/doInsert")
    public String insertRegion(Model model, Region region) {
        log.info("###in MainController-insertRegion()###");
        int result = mainFacade.insertRegion(region);
        log.info("insert result is:" + result);
        return "redirect:/main/regions/goList";
    }

    @PostMapping(value = "/regions/doUpdate/{regionId}")
    public String updateRegionByRegionId(Model model, @PathVariable("regionId") String regionId, Region region) {
        region.setRegionId(regionId);
        int result = mainFacade.updateRegionByRegionId(region);
        log.info("update result is:" + result);
        return "redirect:/main/regions/goList";
    }

    @GetMapping(value = "/regions/doDelete/{regionId}")
    public String deleteRegionByRegionId(Model model, @PathVariable("regionId") String regionId) {
        log.info("###in MainController-insertRegion()###");
        int result = mainFacade.deleteRegionByRegionId(regionId);
        log.info("delete result is:" + result);
        List<Region> regions = mainFacade.selectRegionList();
        return "redirect:/main/regions/goList";
    }

}

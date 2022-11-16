//package com.oracle.springbootsession;
//
//import com.oracle.springbootsession.entity.Region;
//import com.oracle.springbootsession.mapper.RegionMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@SpringBootTest
//public class SpringbootSessionApplicationTest {
//
//    @Resource
//    RegionMapper regionMapper;
//
//    @Test
//    public void testSelectList() {
//        List<Region> regions = regionMapper.selectRegionList();
//        regions.forEach(System.out::println);
//    }
//
//    @Test
//    public void testSelectById() {
//        Region region = regionMapper.selectRegionByRegionId(1);
//        System.out.println(region);
//    }
//
//    @Test
//    public void testInsert() {
//        Region region = new Region();
//        region.setRegionId(5);
//        region.setRegionName("Europe5");
//        regionMapper.insertRegion(region);
//        System.out.println(region);
//    }
//
//    @Test
//    public void testUpdateById() {
//        Region region = new Region();
//        region.setRegionId(5);
//        region.setRegionName("Europe55");
//        regionMapper.updateRegionByRegionId(region);
//        System.out.println(region);
//    }
//
//    @Test
//    public void testDeleteById() {
//        regionMapper.deleteRegionByRegionId(5);
//    }
//}

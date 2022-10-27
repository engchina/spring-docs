package com.example.springbootdemo.controller;

import com.example.springbootdemo.entity.Region;
import com.example.springbootdemo.mapper.Region2Mapper;
import com.example.springbootdemo.mapper.RegionMapper;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.identity.IdentityClient;
import com.oracle.bmc.identity.model.AvailabilityDomain;
import com.oracle.bmc.identity.requests.ListAvailabilityDomainsRequest;
import com.oracle.bmc.identity.responses.ListAvailabilityDomainsResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    @Resource
    RegionMapper regionMapper;

    @Resource
    Region2Mapper region2Mapper;

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello Spring Boot V5!";
    }

    @RequestMapping("/regions/1")
    public Region getOneRegion() {
        Region oneRegion = new Region();
        oneRegion.setRegionId("1");
        oneRegion.setRegionName("tokyo");
        return oneRegion;
    }

    @RequestMapping("/regions")
    public List<Region> getAll() {
        return regionMapper.selectAll();
    }

    @RequestMapping("/regions2")
    public List<Region> getAll2() {
        return region2Mapper.selectList(null);
    }

    @RequestMapping("/ads")
    public List<AvailabilityDomain> getAllAD() throws IOException {
        String region = "eu-madrid-1";
//        String region = "ap-chuncheon-1";
        String rootCompartment = "ocid1.tenancy.oc1..aaaaaaaaro7aox2fclu4urtpgsbacnrmjv46e7n4fw3sc2wbq24l7dzf3kba";

        ConfigFileReader.ConfigFile configFile = ConfigFileReader.parse("/home/oracle/.oci/config", "DEFAULT");
        AuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider(configFile);

        IdentityClient identityClient = new IdentityClient(provider);
        identityClient.setRegion(com.oracle.bmc.Region.fromRegionId(region));

        ListAvailabilityDomainsRequest listAvailabilityDomainsRequest = ListAvailabilityDomainsRequest.builder().compartmentId(rootCompartment).build();
        ListAvailabilityDomainsResponse listAvailabilityDomainsResponse = identityClient.listAvailabilityDomains(listAvailabilityDomainsRequest);
        final List<AvailabilityDomain> items = listAvailabilityDomainsResponse.getItems();
        return items;
    }
}

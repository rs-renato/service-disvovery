package com.demo.region.client;

import com.demo.region.dto.CountryDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RibbonClient("country-service")
@FeignClient("country-service")
public interface CountryServiceProxy {

    @GetMapping("country/region/{id}")
    List<CountryDTO> findByRegionId(@PathVariable("id") Integer id);
}
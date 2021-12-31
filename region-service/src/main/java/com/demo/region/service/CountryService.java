package com.demo.region.service;

import com.demo.region.client.CountryServiceProxy;
import com.demo.region.dto.CountryDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryService {

    private CountryServiceProxy countryServiceProxy;

    @Autowired
    public CountryService(CountryServiceProxy countryServiceProxy) {
        this.countryServiceProxy = countryServiceProxy;
    }

    @HystrixCommand(fallbackMethod= "findByRegionIdFallback")
    public List<CountryDTO> findByRegionId(Integer id) {
        return countryServiceProxy.findByRegionId(id);
    }

    public List<CountryDTO>  findByRegionIdFallback(Integer id, Throwable throwable){
        log.error("calling fallback..", throwable);
        return List.of(new CountryDTO());
    }
}

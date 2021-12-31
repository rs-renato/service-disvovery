package com.demo.region.controller;

import com.demo.region.dto.RegionDTO;
import com.demo.region.model.Region;
import com.demo.region.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> findAll(){
        log.info("find all regions..");
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public Region findById(@PathVariable("id") Integer id){
        log.info("find region by id {}..", id);
        return regionService.findById(id);
    }

    @GetMapping("/{id}/countries")
    public RegionDTO findByIdAndCountries(@PathVariable("id") Integer id){
        log.info("find region by id {} and it'' countries..", id);
        return regionService.findByIdAndCountries(id);
    }
}
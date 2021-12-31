package com.demo.registry.country.controller;

import com.demo.registry.country.model.Country;
import com.demo.registry.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    private CountryService service;

    @Autowired
    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Country> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") String id){
        return service.findById(id);
    }

    @GetMapping("/region/{id}")
    public List<Country> findByRegionId(@PathVariable("id") Integer id){
        return service.findByRegionId(id);
    }
}
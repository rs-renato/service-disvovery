package com.demo.registry.country.service;

import com.demo.registry.country.model.Country;
import com.demo.registry.country.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CountryService {

    private CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public List<Country> findAll(){
        log.info("listing all");
        return this.repository.findAll();
    }

    public Country findById(String id){
        log.info("finding id {}", id);
        return this.repository.findById(id).orElse(null);
    }

    public List<Country> findByRegionId(Integer id){
        log.info("finding by region id {}", id);
        return this.repository.findByRegionId(id);
    }
}

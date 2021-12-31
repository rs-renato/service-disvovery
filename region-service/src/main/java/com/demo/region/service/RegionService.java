package com.demo.region.service;

import com.demo.region.dto.CountryDTO;
import com.demo.region.dto.RegionDTO;
import com.demo.region.model.Region;
import com.demo.region.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RegionService {

    private CountryService countryService;
    private RegionRepository repository;

    @Autowired
    public RegionService(RegionRepository repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    public List<Region> findAll(){
        return this.repository.findAll();
    }

    public Region findById(Integer id){
        return this.repository.findById(id).orElse(null);
    }

    public RegionDTO findByIdAndCountries(Integer id){
        List<CountryDTO> countries = countryService.findByRegionId(id);
        Region region = this.repository.getById(id);
        return new RegionDTO(region.getId(), region.getName(), countries);
    }
}

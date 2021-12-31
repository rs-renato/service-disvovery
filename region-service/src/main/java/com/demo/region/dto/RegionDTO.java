package com.demo.region.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegionDTO {
    private Integer id;
    private String name;
    private List<CountryDTO> countries;
}

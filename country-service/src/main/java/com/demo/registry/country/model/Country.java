package com.demo.registry.country.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "countries")
public class Country {
    @Id
    private String id;
    private String name;
    private Integer regionId;
}

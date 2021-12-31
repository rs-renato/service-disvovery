package com.demo.region.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "regions")
public class Region {
    @Id
    private Integer id;
    private String name;
}
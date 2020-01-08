package com.kkyu.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "tb_group")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Order> orders;
}
package me.d4ve.iot.homeenv.service;

import me.d4ve.iot.homeenv.model.City;

import java.util.List;

public interface ICityService {

    List<City> findAll();
}

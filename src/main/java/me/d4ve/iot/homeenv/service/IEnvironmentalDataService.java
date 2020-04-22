package me.d4ve.iot.homeenv.service;

import me.d4ve.iot.homeenv.model.EnvironmentalData;

import java.util.List;

public interface IEnvironmentalDataService {
    List<EnvironmentalData> findAll();

    void insert(EnvironmentalData data);
}

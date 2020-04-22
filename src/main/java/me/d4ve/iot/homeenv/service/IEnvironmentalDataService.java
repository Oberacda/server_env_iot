package me.d4ve.iot.homeenv.service;

import java.util.List;
import me.d4ve.iot.homeenv.model.EnvironmentalData;

public interface IEnvironmentalDataService {
  List<EnvironmentalData> findAll();

  void insert(EnvironmentalData data);
}

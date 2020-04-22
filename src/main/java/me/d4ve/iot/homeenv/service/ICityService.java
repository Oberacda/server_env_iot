package me.d4ve.iot.homeenv.service;

import java.util.List;
import me.d4ve.iot.homeenv.model.City;

public interface ICityService {

  List<City> findAll();
}

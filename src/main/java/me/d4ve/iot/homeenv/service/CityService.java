package me.d4ve.iot.homeenv.service;

import java.util.List;
import me.d4ve.iot.homeenv.model.City;
import me.d4ve.iot.homeenv.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements ICityService {

  @Autowired private CityRepository repository;

  @Override
  public List<City> findAll() {

    var cities = (List<City>) repository.findAll();

    return cities;
  }
}

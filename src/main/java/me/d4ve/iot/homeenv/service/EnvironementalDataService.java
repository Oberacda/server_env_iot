package me.d4ve.iot.homeenv.service;

import java.util.List;
import me.d4ve.iot.homeenv.model.EnvironmentalData;
import me.d4ve.iot.homeenv.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvironementalDataService implements IEnvironmentalDataService {

  @Autowired private EnvironmentalDataRepository repository;

  @Override
  public List<EnvironmentalData> findAll() {
    return (List<EnvironmentalData>) repository.findAll();
  }

  @Override
  public void insert(EnvironmentalData data) {
    repository.save(data);
  }
}

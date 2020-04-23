package me.d4ve.iot.homeenv.service;

import java.util.List;
import me.d4ve.iot.homeenv.model.EnvironmentalData;
import me.d4ve.iot.homeenv.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EnvironementalDataService implements IEnvironmentalDataService {

  @Autowired private EnvironmentalDataRepository repository;

  @Override
  public @NonNull List<EnvironmentalData> findAll() {
    return (List<EnvironmentalData>) repository.findAll();
  }

  @Override
  public void insert(@NonNull EnvironmentalData data) {
    repository.save(data);
  }
}

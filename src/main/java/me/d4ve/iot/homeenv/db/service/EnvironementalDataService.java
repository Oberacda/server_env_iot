package me.d4ve.iot.homeenv.db.service;

import java.util.List;
import me.d4ve.iot.homeenv.db.model.EnvironmentalData;
import me.d4ve.iot.homeenv.db.repository.EnvironmentalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * Implementation of a access service for {@link EnvironmentalData} instances.
 *
 * <p>This is coupled to a {@link EnvironmentalDataRepository} from where it reads and writes its
 * data.
 */
@Service
public class EnvironementalDataService implements IEnvironmentalDataService {

  /** Repository used for data access. */
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

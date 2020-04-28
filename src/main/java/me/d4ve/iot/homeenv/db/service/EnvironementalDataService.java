package me.d4ve.iot.homeenv.db.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.stream.Collectors;

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

  @Override
  @NonNull
  public List<EnvironmentalData> findAllBefore(@NonNull Timestamp from, @NonNull Duration duration) {
    List<EnvironmentalData> allDataPoints = this.findAll();

    return allDataPoints.parallelStream().filter(x -> {
      if(x.getTimestamp().after(from))
        return false;
      else {
        long difference = from.getTime() - x.getTimestamp().getTime();
        Duration differenceDuration = Duration.of(difference, ChronoUnit.MILLIS);

        return duration.compareTo(differenceDuration) >= 0;
      }
    }).collect(Collectors.toList());
  }
}

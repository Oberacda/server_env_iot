package me.d4ve.iot.homeenv.db.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import me.d4ve.iot.homeenv.db.model.EnvironmentalData;
import org.springframework.lang.NonNull;

/**
 * Interface representing a data access service to the EnvironmentalData in the database.
 *
 * @author David Oberacker
 */
public interface IEnvironmentalDataService {

  /**
   * Function to return all data form the database.
   *
   * @return List of all environmental data in the database.
   */
  @NonNull
  List<EnvironmentalData> findAll();

  /**
   * Function to insert a single environmental datapoint into the database
   *
   * @param data The data to be inserted. Should not be null.
   */
  void insert(@NonNull EnvironmentalData data);

  /**
   * Function to get all data points that are a maximum of {@code duration} before the timestamp {@code from}.
   * @param from The timestamp all the other data points should be before.
   * @param duration The maximum duration the timestamps can be before.
   * @return A list of all data points that match the criteria.
   */
  @NonNull
  List<EnvironmentalData> findAllBefore(@NonNull Timestamp from, Duration duration);
}

package me.d4ve.iot.homeenv.repository;

import me.d4ve.iot.homeenv.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {}

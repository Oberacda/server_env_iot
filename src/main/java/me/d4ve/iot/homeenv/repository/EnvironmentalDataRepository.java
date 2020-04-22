package me.d4ve.iot.homeenv.repository;

import me.d4ve.iot.homeenv.model.EnvironmentalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentalDataRepository extends CrudRepository<EnvironmentalData, Long> {}

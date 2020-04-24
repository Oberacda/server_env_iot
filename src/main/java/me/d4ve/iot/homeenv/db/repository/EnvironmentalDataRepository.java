package me.d4ve.iot.homeenv.db.repository;

import me.d4ve.iot.homeenv.db.model.EnvironmentalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentalDataRepository extends CrudRepository<EnvironmentalData, Long> {}

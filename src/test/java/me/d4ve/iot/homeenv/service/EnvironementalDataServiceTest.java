package me.d4ve.iot.homeenv.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;
import me.d4ve.iot.homeenv.model.EnvironmentalData;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsIterableContaining;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/schema-h2.sql")
class EnvironementalDataServiceTest {

  @Autowired private IEnvironmentalDataService environmentalDataService;

  @Test
  void databaseAccess() {
    var databaseElements = environmentalDataService.findAll();

    assertThat(databaseElements, IsEmptyCollection.empty());
    EnvironmentalData data = new EnvironmentalData();

    data.setUvIndex(1.0);
    data.setIlluminance(100.0);

    Timestamp epochTs = Timestamp.from(Instant.ofEpochSecond(19779));
    data.setTimestamp(epochTs);

    environmentalDataService.insert(data);
    databaseElements = environmentalDataService.findAll();
    assertThat(databaseElements, IsNot.not(IsEmptyCollection.empty()));
    assertThat(databaseElements, IsCollectionWithSize.hasSize(1));
    assertThat(databaseElements, IsIterableContaining.hasItem(data));
  }
}

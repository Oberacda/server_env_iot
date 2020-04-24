package me.d4ve.iot.homeenv.util;

import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import me.d4ve.iot.homeenv.db.model.EnvironmentalData;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnvironmentalDataDeserializerTest {

  ObjectMapper objectMapper;

  URL jsonEnvDataObjectURL;

  @BeforeEach
  void setUp() throws IOException {
    ClassLoader classLoader = EnvironmentalDataDeserializerTest.class.getClassLoader();
    this.jsonEnvDataObjectURL = classLoader.getResource("environmental_data_test.json");

    assertThat(this.jsonEnvDataObjectURL, IsNull.notNullValue());

    this.objectMapper = new ObjectMapper();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void deserialize() throws IOException {
    EnvironmentalData data =
        objectMapper.readValue(this.jsonEnvDataObjectURL, EnvironmentalData.class);

    Timestamp timestamp = Timestamp.from(Instant.ofEpochSecond(1997));

    assertThat(data.getTimestamp(), Is.is(timestamp));
    assertThat(data.getTemperature(), Is.is(10.0));
    assertThat(data.getHumidity(), Is.is(11.0));
    assertThat(data.getPressure(), Is.is(12.0));
    assertThat(data.getIlluminance(), Is.is(13.0));
    assertThat(data.getUva(), Is.is(14.0));
    assertThat(data.getUvb(), Is.is(15.0));
    assertThat(data.getUvIndex(), Is.is(16.0));
  }
}

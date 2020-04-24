package me.d4ve.iot.homeenv.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnvironmentalDataTest {

  private EnvironmentalData testData;
  private EnvironmentalData testDataOther;

  @BeforeEach
  void setUp() {
    testData = new EnvironmentalData();
    testDataOther = new EnvironmentalData();
  }

  @Test
  void timestamp() {
    Timestamp epochNull = Timestamp.from(Instant.ofEpochSecond(1));
    testData.setTimestamp(epochNull);
    assertEquals(epochNull, testData.getTimestamp());

    // Test for equality regarding timestamps.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setTimestamp(epochNull);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void temperature() {
    testData.setTemperature(100.0);
    assertEquals(100.0, testData.getTemperature());

    // Test for equality regarding temperature.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setTemperature(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void humidity() {
    testData.setHumidity(100.0);
    assertEquals(100.0, testData.getHumidity());

    // Test for equality regarding humidity.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setHumidity(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void pressure() {
    testData.setPressure(100.0);
    assertEquals(100.0, testData.getPressure());

    // Test for equality regarding pressure.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setPressure(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void illuminance() {
    testData.setIlluminance(100.0);
    assertEquals(100.0, testData.getIlluminance());

    // Test for equality regarding illuminance.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setIlluminance(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void uva() {
    testData.setUva(100.0);
    assertEquals(100.0, testData.getUva());

    // Test for equality regarding uva.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setUva(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void uvb() {
    testData.setUvb(100.0);
    assertEquals(100.0, testData.getUvb());

    // Test for equality regarding uvb.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setUvb(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void uvIndex() {
    testData.setUvIndex(100.0);
    assertEquals(100.0, testData.getUvIndex());

    // Test for equality regarding zv index.
    assertThat(testData, IsNot.not(testDataOther));
    testDataOther.setUvIndex(100.0);
    assertThat(testData, Is.is(testDataOther));
  }

  @Test
  void testEquals() {
    assertThat(testData, Is.is(testData));

    assertThat(testData, Is.is(testDataOther));
    Timestamp epochNotNull = Timestamp.from(Instant.ofEpochSecond(1));

    testData.setTimestamp(epochNotNull);
    assertThat(testData, IsNot.not(testDataOther));

    assertThat(testData, IsNot.not(IsEqual.equalTo(null)));
    Double test = 10d;

    assertThat(testData, IsNot.not(IsEqual.equalTo(test)));
  }

  @Test
  void testHashCode() {
    HashSet<EnvironmentalData> testSet = new HashSet<>();
    assertThat(testData, Is.is(testDataOther));

    testSet.add(testData);
    testSet.add(testDataOther);

    assertThat(testSet, IsCollectionWithSize.hasSize(1));

    testSet = new HashSet<>();

    Timestamp epochNull = Timestamp.from(Instant.ofEpochSecond(1000));
    testData.setTimestamp(epochNull);

    testSet.add(testData);
    testSet.add(testDataOther);
    assertThat(testSet, IsCollectionWithSize.hasSize(2));
  }
}

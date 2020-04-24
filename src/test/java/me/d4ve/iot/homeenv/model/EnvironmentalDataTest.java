package me.d4ve.iot.homeenv.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

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
        Timestamp epochNull = Timestamp.from(Instant.ofEpochSecond(0));
        testData.setTimestamp(epochNull);
        assertEquals(epochNull, testData.getTimestamp());
    }

    @Test
    void temperature() {
        testData.setTemperature(100.0);
        assertEquals(100.0, testData.getTemperature());
    }

    @Test
    void humidity() {
        testData.setHumidity(100.0);
        assertEquals(100.0, testData.getHumidity());
    }

    @Test
    void pressure() {
        testData.setPressure(100.0);
        assertEquals(100.0, testData.getPressure());
    }

    @Test
    void illuminance() {
        testData.setIlluminance(100.0);
        assertEquals(100.0, testData.getIlluminance());
    }

    @Test
    void uva() {
        testData.setUva(100.0);
        assertEquals(100.0, testData.getUva());
    }

    @Test
    void uvb() {
        testData.setUvb(100.0);
        assertEquals(100.0, testData.getUvb());
    }

    @Test
    void uvIndex() {
        testData.setUvIndex(100.0);
        assertEquals(100.0, testData.getUvIndex());
    }

    @Test
    void testEquals() {
        assertEquals(testData, testDataOther);
        Timestamp epochNull = Timestamp.from(Instant.ofEpochSecond(0));
        testData.setTimestamp(epochNull);
        assertNotEquals(testData,testDataOther);
    }
}

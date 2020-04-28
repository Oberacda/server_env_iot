package me.d4ve.iot.homeenv.rest.transferobjects;

import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TemperatureTransferObject {
  private LocalDateTime timestamp;
  private double temperature;

  public TemperatureTransferObject(LocalDateTime timestamp, double temperature) {
    this.timestamp = timestamp;
    this.temperature = temperature;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public double getTemperature() {
    return temperature;
  }
}

package me.d4ve.iot.homeenv.rest.transferobjects;

import java.sql.Timestamp;

public class TemperatureTransferObject {
  private Timestamp timestamp;
  private double temperature;

  public TemperatureTransferObject(Timestamp timestamp, double temperature) {
    this.timestamp = timestamp;
    this.temperature = temperature;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public double getTemperature() {
    return temperature;
  }
}

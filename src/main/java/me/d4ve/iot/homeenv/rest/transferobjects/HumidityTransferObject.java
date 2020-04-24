package me.d4ve.iot.homeenv.rest.transferobjects;

import java.sql.Timestamp;

public class HumidityTransferObject {
  private Timestamp timestamp;
  private double humidity;

  public HumidityTransferObject(Timestamp timestamp, double humidity) {
    this.timestamp = timestamp;
    this.humidity = humidity;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public double getHumidity() {
    return humidity;
  }
}

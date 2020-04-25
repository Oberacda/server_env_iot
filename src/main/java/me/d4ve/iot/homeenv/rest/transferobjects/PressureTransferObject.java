package me.d4ve.iot.homeenv.rest.transferobjects;

import java.sql.Timestamp;

public class PressureTransferObject {
  private Timestamp timestamp;
  private double pressure;

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public double getPressure() {
    return pressure;
  }

  public PressureTransferObject(Timestamp timestamp, double pressure) {
    this.timestamp = timestamp;
    this.pressure = pressure;
  }
}

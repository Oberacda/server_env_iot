package me.d4ve.iot.homeenv.rest.transferobjects;

import java.sql.Timestamp;

public class IlluminanceTransferObject {
  private Timestamp timestamp;
  private double illuminance;

  public IlluminanceTransferObject(Timestamp timestamp, double illuminance) {
    this.timestamp = timestamp;
    this.illuminance = illuminance;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public double getIlluminance() {
    return illuminance;
  }
}

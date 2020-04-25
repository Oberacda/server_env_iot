package me.d4ve.iot.homeenv.rest.transferobjects;

import java.sql.Timestamp;

public class UvTransferObject {
  private Timestamp timestamp;
  private double uva;
  private double uvb;
  private double uvIndex;

  public UvTransferObject(Timestamp timestamp, double uva, double uvb, double uvIndex) {
    this.timestamp = timestamp;
    this.uva = uva;
    this.uvb = uvb;
    this.uvIndex = uvIndex;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public double getUva() {
    return uva;
  }

  public double getUvb() {
    return uvb;
  }

  public double getUvIndex() {
    return uvIndex;
  }
}

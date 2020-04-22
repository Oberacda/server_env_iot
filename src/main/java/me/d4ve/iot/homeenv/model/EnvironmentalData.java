package me.d4ve.iot.homeenv.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;
import me.d4ve.iot.homeenv.EnvironementalDataDeserializer;

@Entity
@Table(name = "env")
@JsonDeserialize(using = EnvironementalDataDeserializer.class)
public class EnvironmentalData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Timestamp timestamp;

  private Double temperature;

  private Double humidity;
  private Double pressure;
  private Double illuminance;
  private Double uva;
  private Double uvb;
  private Double uv_index;

  public EnvironmentalData() {}

  public EnvironmentalData(
      Timestamp timestamp,
      Double temperature,
      Double humidity,
      Double pressure,
      Double illuminance,
      Double uva,
      Double uvb,
      Double uv_index) {
    this.timestamp = timestamp;
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    this.illuminance = illuminance;
    this.uva = uva;
    this.uvb = uvb;
    this.uv_index = uv_index;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public void setHumidity(Double humidity) {
    this.humidity = humidity;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public void setIlluminance(Double illuminance) {
    this.illuminance = illuminance;
  }

  public void setUva(Double uva) {
    this.uva = uva;
  }

  public void setUvb(Double uvb) {
    this.uvb = uvb;
  }

  public void setUv_index(Double uv_index) {
    this.uv_index = uv_index;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public Double getTemperature() {
    return temperature;
  }

  public Double getHumidity() {
    return humidity;
  }

  public Double getPressure() {
    return pressure;
  }

  public Double getIlluminance() {
    return illuminance;
  }

  public Double getUva() {
    return uva;
  }

  public Double getUvb() {
    return uvb;
  }

  public Double getUv_index() {
    return uv_index;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EnvironmentalData that = (EnvironmentalData) o;
    return id.equals(that.id)
        && getTimestamp().equals(that.getTimestamp())
        && getTemperature().equals(that.getTemperature())
        && getHumidity().equals(that.getHumidity())
        && getPressure().equals(that.getPressure())
        && getIlluminance().equals(that.getIlluminance())
        && getUva().equals(that.getUva())
        && getUvb().equals(that.getUvb())
        && getUv_index().equals(that.getUv_index());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        getTimestamp(),
        getTemperature(),
        getHumidity(),
        getPressure(),
        getIlluminance(),
        getUva(),
        getUvb(),
        getUv_index());
  }
}

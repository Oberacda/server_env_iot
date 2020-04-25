package me.d4ve.iot.homeenv.db.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.*;
import me.d4ve.iot.homeenv.util.EnvironmentalDataDeserializer;

/**
 * Database entry representing a single record of environmental data.
 *
 * <p>A single record consists of a temperature, pressure, humidity, illuminance, and uv reading at
 * a fixed unix epoch timestamp.
 *
 * @author David Oberacker
 */
@Entity
@Table(name = "env")
@JsonDeserialize(using = EnvironmentalDataDeserializer.class)
public class EnvironmentalData {

  /**
   * Unique database id of a probe.
   *
   * <p>This field is autogenerated by the database system and should not be used in business logic.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  /**
   * Timestamp the data point was recorded at. It is represented as the seconds since the unix
   * epoch.
   */
  @Column(name = "timestamp")
  private Timestamp timestamp;

  /** Temperature value recorded for the data point. This is reported in °C. */
  @Column(name = "temperature")
  private double temperature;

  /** Relative humidity value recorded for the data point. This is reported in %RH. */
  @Column(name = "humidity")
  private double humidity;

  /** Pressure value recorded for the data point. This is reported in kPa. */
  @Column(name = "pressure")
  private double pressure;

  /** Illuminance value recorded for the data point. This is reported in lux. */
  @Column(name = "illuminance")
  private double illuminance;

  /** UVA value recorded for the data point. */
  @Column(name = "uva")
  private double uva;

  /** UVB value recorded for the data point. */
  @Column(name = "uvb")
  private double uvb;

  /** UV index value recorded for the data point. */
  @Column(name = "uv_index")
  private double uvIndex;

  /**
   * Creates a new environmental data element.
   *
   * <p>This element is not directly inserted into the database. The default values for all data
   * attributes is 0. The default timestamp is the unix epoch.
   */
  public EnvironmentalData() {
    this.timestamp = Timestamp.from(Instant.ofEpochSecond(0));
  }

  /**
   * Setter for the timestamp the data was recorded at.
   *
   * @param timestamp The new timestamp for the data point.
   */
  @Column(name = "timestamp")
  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  /**
   * Setter for the temperature value.
   *
   * @param temperature The new temperature value in °C.
   */
  @Column(name = "temperature")
  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  /**
   * Setter for the humidity value.
   *
   * @param humidity The new humidity value in %RH.
   */
  @Column(name = "humidity")
  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

  /**
   * Setter for the pressure value.
   *
   * @param pressure The new pressure value in kPa.
   */
  @Column(name = "pressure")
  public void setPressure(double pressure) {
    this.pressure = pressure;
  }

  /**
   * Setter for the illuminance value.
   *
   * @param illuminance The new illuminance value in LUX.
   */
  @Column(name = "illuminance")
  public void setIlluminance(double illuminance) {
    this.illuminance = illuminance;
  }

  /**
   * Setter for the uva value.
   *
   * @param uva The new uva value.
   */
  @Column(name = "uva")
  public void setUva(double uva) {
    this.uva = uva;
  }

  /**
   * Setter for the uvb value.
   *
   * @param uvb The new uvb value.
   */
  @Column(name = "uvb")
  public void setUvb(double uvb) {
    this.uvb = uvb;
  }

  /**
   * Setter for the uv index value.
   *
   * @param uvIndex The new uv index value.
   */
  @Column(name = "uv_index")
  public void setUvIndex(double uvIndex) {
    this.uvIndex = uvIndex;
  }

  /**
   * Getter for the timestamp value.
   *
   * @return The timestamp the data was recorded at.
   */
  public Timestamp getTimestamp() {
    return timestamp;
  }

  /**
   * Getter for the temperature value.
   *
   * @return The temperature value in °C.
   */
  public double getTemperature() {
    return temperature;
  }

  /**
   * Getter for the humidity value.
   *
   * @return The humidity value in %RH.
   */
  public double getHumidity() {
    return humidity;
  }

  /**
   * Getter for the pressure value.
   *
   * @return The pressure value in kPa.
   */
  public double getPressure() {
    return pressure;
  }

  /**
   * Getter for the illuminance value.
   *
   * @return The illuminance value in lux.
   */
  public double getIlluminance() {
    return illuminance;
  }

  /**
   * Getter for the uva value.
   *
   * @return The uva value.
   */
  public double getUva() {
    return uva;
  }

  /**
   * Getter for the uvb value.
   *
   * @return The uvb value.
   */
  public double getUvb() {
    return uvb;
  }

  /**
   * Getter of the uv index value.
   *
   * @return The uv index value.
   */
  public double getUvIndex() {
    return uvIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EnvironmentalData that = (EnvironmentalData) o;
    return Double.compare(that.getTemperature(), getTemperature()) == 0
        && Double.compare(that.getHumidity(), getHumidity()) == 0
        && Double.compare(that.getPressure(), getPressure()) == 0
        && Double.compare(that.getIlluminance(), getIlluminance()) == 0
        && Double.compare(that.getUva(), getUva()) == 0
        && Double.compare(that.getUvb(), getUvb()) == 0
        && Double.compare(that.getUvIndex(), getUvIndex()) == 0
        && Objects.equals(getTimestamp(), that.getTimestamp());
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getTimestamp(),
        getTemperature(),
        getHumidity(),
        getPressure(),
        getIlluminance(),
        getUva(),
        getUvb(),
        getUvIndex());
  }
}

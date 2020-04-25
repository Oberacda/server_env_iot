package me.d4ve.iot.homeenv.rest.api;

import java.util.ArrayList;
import java.util.List;
import me.d4ve.iot.homeenv.db.service.IEnvironmentalDataService;
import me.d4ve.iot.homeenv.rest.transferobjects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentalDataController {

  @Autowired private IEnvironmentalDataService environmentalDataService;

  @GetMapping(value = "/env/temperature", produces = "application/json")
  public List<TemperatureTransferObject> getTemperatureValues() {
    var envData = environmentalDataService.findAll();

    List<TemperatureTransferObject> tempData = new ArrayList<>();
    envData.forEach(
        data ->
            tempData.add(
                new TemperatureTransferObject(data.getTimestamp(), data.getTemperature())));

    return tempData;
  }

  @GetMapping(value = "/env/humidity", produces = "application/json")
  public List<HumidityTransferObject> getHumidityValues() {
    var envData = environmentalDataService.findAll();

    List<HumidityTransferObject> humidityData = new ArrayList<>();
    envData.forEach(
        data ->
            humidityData.add(new HumidityTransferObject(data.getTimestamp(), data.getHumidity())));

    return humidityData;
  }

  @GetMapping(value = "/env/pressure", produces = "application/json")
  public List<PressureTransferObject> getPressureValues() {
    var envData = environmentalDataService.findAll();

    List<PressureTransferObject> pressureData = new ArrayList<>();
    envData.forEach(
        data ->
            pressureData.add(new PressureTransferObject(data.getTimestamp(), data.getPressure())));

    return pressureData;
  }

  @GetMapping(value = "/env/illuminance", produces = "application/json")
  public List<IlluminanceTransferObject> getIlluminanceValues() {
    var envData = environmentalDataService.findAll();

    List<IlluminanceTransferObject> illuminanceData = new ArrayList<>();
    envData.forEach(
        data ->
            illuminanceData.add(
                new IlluminanceTransferObject(data.getTimestamp(), data.getIlluminance())));

    return illuminanceData;
  }

  @GetMapping(value = "/env/uv", produces = "application/json")
  public List<UvTransferObject> getUvValues() {
    var envData = environmentalDataService.findAll();

    List<UvTransferObject> uvData = new ArrayList<>();
    envData.forEach(
        data ->
            uvData.add(
                new UvTransferObject(
                    data.getTimestamp(), data.getUva(), data.getUvb(), data.getUvIndex())));

    return uvData;
  }
}

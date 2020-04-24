package me.d4ve.iot.homeenv.rest.api;

import java.util.ArrayList;
import java.util.List;
import me.d4ve.iot.homeenv.db.service.IEnvironmentalDataService;
import me.d4ve.iot.homeenv.rest.transferobjects.HumidityTransferObject;
import me.d4ve.iot.homeenv.rest.transferobjects.TemperatureTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentalDataController {

  @Autowired private IEnvironmentalDataService environmentalDataService;

  @GetMapping(value = "/env/temperature")
  public List<TemperatureTransferObject> getTemperatureValues() {
    var envData = environmentalDataService.findAll();

    List<TemperatureTransferObject> tempData = new ArrayList<>();
    envData.forEach(
        data -> {
          tempData.add(new TemperatureTransferObject(data.getTimestamp(), data.getTemperature()));
        });

    return tempData;
  }

  @GetMapping(value = "/env/humidity")
  public List<HumidityTransferObject> getHumidityValues() {
    var envData = environmentalDataService.findAll();

    List<HumidityTransferObject> humidityData = new ArrayList<>();
    envData.forEach(
        data -> {
          humidityData.add(new HumidityTransferObject(data.getTimestamp(), data.getHumidity()));
        });

    return humidityData;
  }
}

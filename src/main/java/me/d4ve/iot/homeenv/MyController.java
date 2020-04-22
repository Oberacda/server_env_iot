package me.d4ve.iot.homeenv;

import java.util.List;
import me.d4ve.iot.homeenv.model.EnvironmentalData;
import me.d4ve.iot.homeenv.service.IEnvironmentalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

  @Autowired private IEnvironmentalDataService environmentalDataService;

  @GetMapping("/showData")
  public String findCities(Model model) {

    var data = (List<EnvironmentalData>) environmentalDataService.findAll();

    model.addAttribute("env", data);

    return "showData";
  }
}

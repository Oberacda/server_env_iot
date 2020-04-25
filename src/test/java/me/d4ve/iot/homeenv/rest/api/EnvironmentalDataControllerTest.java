package me.d4ve.iot.homeenv.rest.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import me.d4ve.iot.homeenv.rest.transferobjects.*;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema-h2.sql")
@Sql("/data-h2.sql")
class EnvironmentalDataControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void getTemperatureValues() throws Exception {
    String response =
        mockMvc.perform(get("/env/temperature")).andReturn().getResponse().getContentAsString();
    List<TemperatureTransferObject> responseObjects =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertThat(responseObjects, IsCollectionWithSize.hasSize(22));
  }

  @Test
  void getHumidityValues() throws Exception {
    String response =
        mockMvc.perform(get("/env/humidity")).andReturn().getResponse().getContentAsString();
    List<HumidityTransferObject> responseObjects =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertThat(responseObjects, IsCollectionWithSize.hasSize(22));
  }

  @Test
  void getPressureValues() throws Exception {
    String response =
        mockMvc.perform(get("/env/pressure")).andReturn().getResponse().getContentAsString();
    List<PressureTransferObject> responseObjects =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertThat(responseObjects, IsCollectionWithSize.hasSize(22));
  }

  @Test
  void getIlluminanceValues() throws Exception {
    String response =
        mockMvc.perform(get("/env/illuminance")).andReturn().getResponse().getContentAsString();
    List<IlluminanceTransferObject> responseObjects =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertThat(responseObjects, IsCollectionWithSize.hasSize(22));
  }

  @Test
  void getUvValues() throws Exception {
    String response =
        mockMvc.perform(get("/env/uv")).andReturn().getResponse().getContentAsString();
    List<UvTransferObject> responseObjects =
        objectMapper.readValue(response, new TypeReference<>() {});

    assertThat(responseObjects, IsCollectionWithSize.hasSize(22));
  }
}

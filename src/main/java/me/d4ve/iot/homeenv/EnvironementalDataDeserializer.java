package me.d4ve.iot.homeenv;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import me.d4ve.iot.homeenv.model.EnvironmentalData;

public class EnvironementalDataDeserializer extends StdDeserializer<EnvironmentalData> {

  public EnvironementalDataDeserializer() {
    this(null);
  }

  public EnvironementalDataDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public EnvironmentalData deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
    JsonNode node = jp.getCodec().readTree(jp);
    long epochTime = node.get("timestamp").longValue();
    Timestamp timestamp = Timestamp.from(Instant.ofEpochSecond(epochTime));

    Double temperature = node.get("temperature").doubleValue();
    Double humidity = node.get("humidity").doubleValue();
    Double pressure = node.get("pressure").doubleValue();
    Double illuminance = node.get("illuminance").doubleValue();
    Double uva = node.get("uva").doubleValue();
    Double uvb = node.get("uvb").doubleValue();
    Double uvIndex = node.get("uv_index").doubleValue();

    return new EnvironmentalData(
        timestamp, temperature, humidity, pressure, illuminance, uva, uvb, uvIndex);
  }
}

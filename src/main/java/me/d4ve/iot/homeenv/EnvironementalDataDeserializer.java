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
    EnvironmentalData data = new EnvironmentalData();

    long epochTime = node.get("timestamp").longValue();
    data.setTimestamp(Timestamp.from(Instant.ofEpochSecond(epochTime)));

    data.setTemperature(node.get("temperature").doubleValue());
    data.setHumidity(node.get("humidity").doubleValue());
    data.setPressure(node.get("pressure").doubleValue());
    data.setIlluminance(node.get("illuminance").doubleValue());
    data.setUva(node.get("uva").doubleValue());
    data.setUvb(node.get("uvb").doubleValue());
    data.setUvIndex(node.get("uv_index").doubleValue());

    return data;
  }
}

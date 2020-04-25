package me.d4ve.iot.homeenv.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import me.d4ve.iot.homeenv.db.model.EnvironmentalData;

/**
 * Custom jackson deserializer for the {@link EnvironmentalData} class.
 *
 * <p>This is necessary as the timestamp is in epoch seconds and will otherwise be parsed wrong.
 *
 * @author David Oberacker
 */
public class EnvironmentalDataDeserializer extends StdDeserializer<EnvironmentalData> {

  /** Creates a new deserializer instance. */
  public EnvironmentalDataDeserializer() {
    this(null);
  }

  /**
   * Creates a new deserializer instance.
   *
   * @param vc The class to deserialize to.
   */
  public EnvironmentalDataDeserializer(Class<?> vc) {
    super(vc);
  }

  /**
   * Function to deserialize data into a {@link EnvironmentalData} instance.
   *
   * @param jp The JSON data to parse.
   * @param ctxt The context used for parsing the JSON.
   * @return Environmental data extracted from the json package.
   * @throws IOException If the data cannot be read, this exception is thrown.
   */
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

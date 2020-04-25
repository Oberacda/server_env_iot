package me.d4ve.iot.homeenv.cloud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.d4ve.iot.homeenv.db.model.EnvironmentalData;
import me.d4ve.iot.homeenv.db.service.IEnvironmentalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * Class used to connect and receive messages from the Google Cloud Platform PubSub service.
 *
 * @author David Oberacker
 */
@Service
@Profile("!test")
public class GcpSubClient {

  /** Service to connect to the underlying database. */
  @Autowired private IEnvironmentalDataService environmentalDataService;

  /**
   * Bean to create and adapt the subscriptions to local channels.
   *
   * @param inputChannel The message channel the messages should be piped to.
   * @param pubSubTemplate The template for a new message channel to be used.
   * @return A message channel adapter.
   */
  @Bean
  @Profile("!test")
  public PubSubInboundChannelAdapter messageChannelAdapter(
      @Qualifier("envDataInputChannel") MessageChannel inputChannel,
      PubSubTemplate pubSubTemplate) {

    PubSubInboundChannelAdapter adapter =
        new PubSubInboundChannelAdapter(
            pubSubTemplate, "projects/d4ve-me/subscriptions/david-env-sub");

    adapter.setOutputChannel(inputChannel);

    return adapter;
  }

  /**
   * Creates the required message input channel.
   *
   * @return The create message channel.
   */
  @Bean
  @Profile("!test")
  public MessageChannel envDataInputChannel() {

    return new DirectChannel();
  }

  /**
   * Method that is executed when there is a new message available.
   *
   * @param payload The payload of the message.
   * @throws JsonProcessingException If the payload does not match the required JSON format this
   *     exception is thrown.
   */
  @ServiceActivator(inputChannel = "envDataInputChannel")
  @Profile("!test")
  public void messageReceiver(String payload) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    var value = objectMapper.readValue(payload, EnvironmentalData.class);

    environmentalDataService.insert(value);
  }
}

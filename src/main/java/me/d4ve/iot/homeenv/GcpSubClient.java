package me.d4ve.iot.homeenv;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.d4ve.iot.homeenv.model.EnvironmentalData;
import me.d4ve.iot.homeenv.service.IEnvironmentalDataService;
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

@Service
@Profile("!test")
public class GcpSubClient {

  @Autowired private IEnvironmentalDataService environmentalDataService;

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

  @Bean
  @Profile("!test")
  public MessageChannel envDataInputChannel() {

    return new DirectChannel();
  }

  @ServiceActivator(inputChannel = "envDataInputChannel")
  @Profile("!test")
  public void messageReceiver(String payload) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    var value = objectMapper.readValue(payload, EnvironmentalData.class);

    environmentalDataService.insert(value);
  }
}

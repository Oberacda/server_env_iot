package me.d4ve.iot.homeenv;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;

@SpringBootApplication
public class HomeEnvApplication {

  public static void main(String[] args) {
    SpringApplication.run(HomeEnvApplication.class, args);
  }
}

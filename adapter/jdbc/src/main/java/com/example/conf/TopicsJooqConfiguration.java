package com.example.conf;

import com.example.TopicsRecordListener;
import org.jooq.conf.Settings;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class TopicsJooqConfiguration {

  @Bean
  Settings jooqSettings() {
    return new Settings()
      .withMapRecordComponentParameterNames(true);
  }

  @Bean
  DefaultConfigurationCustomizer jooqConfigurationCustomizer(Settings settings, Clock clock) {
    return configuration -> {
      configuration.set(settings);
      configuration.set(new TopicsRecordListener(clock));
    };
  }
}

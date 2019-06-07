package com.nike.cerberus.springboot.testapp;

import com.nike.cerberus.springboot.CerberusClientSpringBootConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(CerberusClientSpringBootConfiguration.class)
public class IntegrationTestSpringAppConfig {
  public IntegrationTestSpringAppConfig() {
    log.info("IntegrationTestSpringAppConfig Loaded");
  }
}

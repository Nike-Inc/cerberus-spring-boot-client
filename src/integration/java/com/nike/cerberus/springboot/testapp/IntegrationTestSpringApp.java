package com.nike.cerberus.springboot.testapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class IntegrationTestSpringApp {

  private final ApplicationContext applicationContext;
  private boolean isRunning = true;

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  public void shutdown() {
    isRunning = false;
  }

  public void run() {
    while (isRunning) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException("Sleep interrupted");
      }
    }
  }

  public IntegrationTestSpringApp() {
    applicationContext = new AnnotationConfigApplicationContext("com.nike.cerberus.springboot.testapp");
  }
}

/*
 * Copyright (c) 2020 Nike, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nike.cerberus.springboot.testapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        applicationContext =
                new AnnotationConfigApplicationContext("com.nike.cerberus.springboot.testapp");
    }
}

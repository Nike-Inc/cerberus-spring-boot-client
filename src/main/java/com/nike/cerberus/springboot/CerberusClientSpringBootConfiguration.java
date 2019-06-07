/*
 * Copyright (c) 2017 Nike, Inc.
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

package com.nike.cerberus.springboot;

import com.nike.cerberus.client.CerberusClient;
import com.nike.cerberus.client.DefaultCerberusClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class that provides a CerberusClient for interacting with Cerberus.
 * <p>
 * This class is configured via {@link CerberusClientSpringBootProperties} which has
 * two required settings for accessing Cerberus, e.g. in your {@code application.properties}:
 * <pre>
 *     cerberus.url=https://test.cerberus.example.com
 *     cerberus.region=us-west-2
 * </pre>
 *
 * @see <a href="http://engineering.nike.com/cerberus/docs/user-guide/quick-start">Cerberus Quick Start Guide</a>
 */
@Configuration
@EnableConfigurationProperties(CerberusClientSpringBootProperties.class)
public class CerberusClientSpringBootConfiguration {

    private CerberusClientSpringBootProperties cerberusClientProperties;

    @Autowired
    public CerberusClientSpringBootConfiguration(CerberusClientSpringBootProperties cerberusClientProperties) {
        this.cerberusClientProperties = cerberusClientProperties;
    }

    /**
     * Instantiate a CerberusClient.
     *
     * @see <a href="http://engineering.nike.com/cerberus/docs/user-guide/quick-start">Cerberus Quick Start Guide</a>
     */
    @Bean
    public CerberusClient cerberusClient() {
        String url = cerberusClientProperties.getUrl();
        String region = cerberusClientProperties.getRegion();
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("cerberus.url setting is required! " +
                    "E.g. Your application needs a configuration property like 'cerberus.url=https://test.cerberus.example.com'");
        } else if (region == null || region.isEmpty()) {
            throw new IllegalArgumentException("cerberus.region setting is required! " +
                    "E.g. Your application needs a configuration property like 'cerberus.region=us-west-2'");
        }
        return DefaultCerberusClientFactory.getClient(url, region);
    }
}

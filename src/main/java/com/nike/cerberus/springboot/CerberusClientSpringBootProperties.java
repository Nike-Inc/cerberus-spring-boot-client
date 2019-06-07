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


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * A {@link ConfigurationProperties} companion for {@link CerberusClientSpringBootProperties}.
 * <p>
 * This class has two required settings for accessing Cerberus, e.g. in your {@code application.properties}:
 * <pre>
 *     cerberus.url=https://test.cerberus.example.com
 *     cerberus.region=us-west-2
 * </pre>
 *
 * @see <a href="http://engineering.nike.com/cerberus/docs/user-guide/quick-start">Cerberus Quick Start Guide</a>
 */
@ConfigurationProperties("cerberus")
public class CerberusClientSpringBootProperties {

    private String url;
    private String region;

    /**
     * Cerberus URL, e.g. https://test.cerberus.example.com
     */
    public String getUrl() {
        return url;
    }

    /**
     * Cerberus URL, e.g. https://test.cerberus.example.com
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Cerberus region for sts auth, e.g. us-west-2
     */
    public String getRegion() {
        return region;
    }

    /**
     * Cerberus region for sts auth, e.g. us-west-2
     */
    public void setRegion(String region) {
        this.region = region;
    }
}

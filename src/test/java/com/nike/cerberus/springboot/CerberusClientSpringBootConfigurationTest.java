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

package com.nike.cerberus.springboot;

import org.junit.jupiter.api.*;

public class CerberusClientSpringBootConfigurationTest {

    @Test
    public void testForExceptionOnMissingMissingRegion() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CerberusClientSpringBootProperties configProps = new CerberusClientSpringBootProperties();
            configProps.setUrl("https://test.cerberus.example.com");

            new CerberusClientSpringBootConfiguration(configProps)
                    .cerberusClient();
        });
    }

    @Test
    public void testForExceptionOnMissingURL()  {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CerberusClientSpringBootProperties configProps = new CerberusClientSpringBootProperties();
            configProps.setRegion("us-west-2");

            new CerberusClientSpringBootConfiguration(configProps)
                    .cerberusClient();
        });
    }

    @Test
    public void testCerberusClientSpringBootConfiguration()  {
        Assertions.assertDoesNotThrow(() -> {
            CerberusClientSpringBootProperties configProps = new CerberusClientSpringBootProperties();
            configProps.setRegion("us-west-2");
            configProps.setUrl("https://test.cerberus.example.com");
            new CerberusClientSpringBootConfiguration(configProps)
                    .cerberusClient();
        });
    }
}

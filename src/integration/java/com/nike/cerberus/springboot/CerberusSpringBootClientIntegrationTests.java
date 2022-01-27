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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fieldju.commons.EnvUtils;
import com.nike.cerberus.client.CerberusClient;
import com.nike.cerberus.client.CerberusServerApiException;
import com.nike.cerberus.client.CerberusServerException;
import com.nike.cerberus.client.model.CerberusListResponse;
import com.nike.cerberus.client.model.CerberusResponse;
import com.nike.cerberus.springboot.testapp.IntegrationTestSpringApp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CerberusSpringBootClientIntegrationTests {

    private IntegrationTestSpringApp app;

    private String cerberusRootSdbPath;
    private String secretPath;
    private String sdbFullSecretPath;
    private Map<String, String> secretData;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    @BeforeEach
    public void before() {
        EnvUtils.getRequiredEnv("CERBERUS_URL");
        EnvUtils.getRequiredEnv("CERBERUS_REGION");
        cerberusRootSdbPath = EnvUtils.getRequiredEnv("SDB_ROOT_PATH");

        secretPath = UUID.randomUUID().toString();
        sdbFullSecretPath = cerberusRootSdbPath + secretPath;

        String key = RandomStringUtils.randomAlphabetic(15);
        String value = RandomStringUtils.randomAlphabetic(25);
        secretData = new HashMap<>();
        secretData.put(key, value);

        app = new IntegrationTestSpringApp();

        executorService.submit(() -> app.run());
    }

    @AfterEach
    public void after() {
        app.shutdown();
    }

    @Test
    public void test_that_the_cerberus_springboot_client_can_be_used_in_a_spring_app() {
        CerberusClient cerberusClient = app.getApplicationContext().getBean(CerberusClient.class);

        // create secret
        cerberusClient.write(sdbFullSecretPath, secretData);

        // read secret
        CerberusResponse cerberusReadResponse = cerberusClient.read(sdbFullSecretPath);
        assertEquals(secretData, cerberusReadResponse.getData());

        // list secrets
        CerberusListResponse cerberusListResponse = cerberusClient.list(cerberusRootSdbPath);
        assertTrue(cerberusListResponse.getKeys().contains(secretPath));

        // update secret
        Map<String, String> newSecretData = generateNewSecretData();
        cerberusClient.write(sdbFullSecretPath, newSecretData);
        secretData = newSecretData;

        // confirm updated secret data
        CerberusResponse cerberusReadResponseUpdated = cerberusClient.read(sdbFullSecretPath);
        assertEquals(newSecretData, cerberusReadResponseUpdated.getData());

        // delete secret
        cerberusClient.delete(sdbFullSecretPath);

        // confirm secret is deleted
        try {
            cerberusClient.read(sdbFullSecretPath);
        } catch (CerberusServerApiException cse) {
            assertEquals(404, cse.getCode());
        }
    }

    private Map<String, String> generateNewSecretData() {
        String key = RandomStringUtils.randomAlphabetic(20);
        String value = RandomStringUtils.randomAlphabetic(30);
        Map<String, String> newSecretData = new HashMap<>();
        newSecretData.put(key, value);

        return newSecretData;
    }
}

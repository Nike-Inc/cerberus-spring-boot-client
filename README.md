# Cerberus Spring Boot Client

![][gh actions img]
[![codecov](https://codecov.io/gh/Nike-Inc/cerberus-spring-boot-client/branch/master/graph/badge.svg)](https://codecov.io/gh/Nike-Inc/cerberus-spring-boot-client)
[![][license img]][license]

A Spring Boot client library for Cerberus for Spring Boot applications.

To learn more about Cerberus, please see the [Cerberus website](http://engineering.nike.com/cerberus/).

## Publishing Notice 3/17/2021
As of spring 2021, JFrog has decided to sunset Bintray and JCenter.
Due to this decision, we are pausing our open source publishing of the Cerberus Spring Boot Client.
However, we will still be updating the source code and making new GitHub releases.

In order to build the jar yourself, run this command:
```bash
./gradlew assemble
```

The jar will be located in `./build/libs/`.

For any questions or concerns, create a Github issue [here](https://github.com/Nike-Inc/cerberus-spring-boot-client/issues/new).

## Quickstart for EC2

1. Start with the [quick start guide](http://engineering.nike.com/cerberus/docs/user-guide/quick-start).
2. Add the Cerberus Spring Boot Client dependency to your build (e.g. Artifactory) or build the jar
3. Provide an authentication mechanism.
   - For local development it is easiest to export a `CERBERUS_TOKEN` that you copied from the Cerberus dashboard.
     When running in AWS, your application will not need this environmetal variable, instead it will automatically 
     authenticate using its IAM role. Alternatively, set a `cerberus.token` System property.
   - If you would like to test IAM authentication locally, you can do that by [assuming a role](http://docs.aws.amazon.com/cli/latest/userguide/cli-roles.html).
4. Configure the Cerberus URL and region, e.g. in your `application.properties`
```
    cerberus.url=https://test.cerberus.example.com/
    cerberus.region=us-west-2
```
5. Ensure `CerberusClientSpringBootConfiguration` is registered in your `ApplicationContext`. E.g.
```java
    @Configuration
    @Import(CerberusClientSpringBootConfiguration.class)
    public class MyAppSpringConfig {
    }
```
6. Access secrets from Cerberus using Java client
``` java
    @Autowired
    CerberusClient cerberusClient;
    
    String path = "/app/my-sdb-name"; // path from Cerberus dashboard
    Map<String,String> secrets = cerberusClient.read(path).getData();
    String secret = secrets.get("propname");  // property name from Cerberus dashboard
```

# Development

### Run Integration Tests
First, make sure you have a safe deposit box created in the cerberus environment you plan to run the tests against.
The credentials you plan to use to execute the tests with must have write permissions to this SDB. 

Second, make sure the following environment variables are set before running the Cerberus Spring Boot Client integration tests:

``` bash
    export CERBERUS_URL=https://example.cerberus.com
    export CERBERUS_REGION=us-west-2
    export SDB_ROOT_PATH=app/integration-test-sdb/
```

Then, make sure AWS credentials have been loaded into the default credential profile or a cerberus token has been exported
to the "CERBERUS_TOKEN" environment variable. 

One method of obtaining credentials is by running [gimme-aws-creds](https://github.com/Nike-Inc/gimme-aws-creds):

```bash
    gimme-aws-creds
```

Next, in the project directory run:
```gradle
    ./gradlew integration
```

<a name="license"></a>
## License

Cerberus client is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

[gh actions img]:https://github.com/Nike-Inc/cerberus-spring-boot-client/workflows/Build/badge.svg?branch=master

[license]:LICENSE.txt
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg

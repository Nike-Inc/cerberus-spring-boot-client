# Cerberus Spring Boot Client

[ ![Download](https://api.bintray.com/packages/nike/maven/cerberus-spring-boot-client/images/download.svg) ](https://bintray.com/nike/maven/cerberus-spring-boot-client/_latestVersion)
[![][travis img]][travis]
[![Coverage Status](https://coveralls.io/repos/github/Nike-Inc/cerberus-spring-boot-client/badge.svg?branch=master)](https://coveralls.io/github/Nike-Inc/cerberus-spring-boot-client)
[![][license img]][license]

A Spring Boot client library for Cerberus for Spring Boot applications.

To learn more about Cerberus, please see the [Cerberus website](http://engineering.nike.com/cerberus/).

## Quickstart for EC2

1. Start with the [quick start guide](http://engineering.nike.com/cerberus/docs/user-guide/quick-start).
2. Add the [Cerberus Spring Boot Client dependency](https://bintray.com/nike/maven/cerberus-spring-boot-client) to your build (e.g. Maven, Gradle)
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

<a name="license"></a>
## License

Cerberus client is released under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

[travis]:https://travis-ci.org/Nike-Inc/cerberus-spring-boot-client
[travis img]:https://api.travis-ci.org/Nike-Inc/cerberus-spring-boot-client.svg?branch=master

[license]:LICENSE.txt
[license img]:https://img.shields.io/badge/License-Apache%202-blue.svg

[toc]:#table_of_contents

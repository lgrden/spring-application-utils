# spring-application-utils @ We Get IT

## Description
Useful spring application utilities and tools:
- [Core Module](core/README.md)
- [Mongo Module](mongo/README.md)
- [Security Module](security/README.md)
- [Swagger Module](swagger/README.md)

## System requirements
 - JDK 11+
 - Maven 3.6.3+
 - Spring Boot 2.6.3+

## Usage
Dependency management in pom.xml
```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.wegetit</groupId>
        <artifactId>spring-application-utils-dependencies</artifactId>
        <version>0.1.14</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```
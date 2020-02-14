FROM maven:3.5.2-jdk-8-alpine as build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn clean install
RUN ls -a target/

FROM jboss/wildfly:18.0.0.Final
  
COPY --from=build tmp/target/SpringBootWithWildFly-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/ROOT.war




FROM maven:3.5.2-jdk-8-alpine as build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
RUN ls -a 

FROM jboss/wildfly:18.0.0.Final
  
COPY --from=build /tmp/target/SpringBootWithWildFly-0.0.1-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/jboss-wildfly-demo.war


CMD ["--deploymentDir", "/opt/payara/deployments", "--contextroot", ""]

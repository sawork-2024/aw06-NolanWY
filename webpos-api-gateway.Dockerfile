FROM maven:latest AS build
WORKDIR /usr/src/app
COPY ./pom.xml ./pom.xml
COPY ./webpos-api/pom.xml ./webpos-api/pom.xml
COPY ./webpos-api-gateway/pom.xml ./webpos-api-gateway/pom.xml
COPY ./webpos-config-server/pom.xml ./webpos-config-server/pom.xml
COPY ./webpos-discovery-server/pom.xml ./webpos-discovery-server/pom.xml
COPY ./webpos-orders-service/pom.xml ./webpos-orders-service/pom.xml
COPY ./webpos-products-service/pom.xml ./webpos-products-service/pom.xml
RUN mvn dependency:go-offline
COPY ./ ./
RUN mvn -B clean package -pl webpos-api-gateway -am -DskipTests=true

FROM eclipse-temurin:21-jre AS run
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/webpos-api-gateway/target/webpos-api-gateway-1.0-SNAPSHOT.jar .
CMD java -server -jar webpos-api-gateway-1.0-SNAPSHOT.jar

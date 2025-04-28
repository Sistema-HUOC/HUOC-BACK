FROM openjdk:17-alpine AS base
WORKDIR app
RUN apk add --no-cache maven

FROM base AS build
COPY . .
RUN mvn install -DskipTests

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
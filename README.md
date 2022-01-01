# Service Discovery POC
This POC is part of article published:
https://www.linkedin.com/pulse/client-side-load-balance-spring-boot-microservice-docker-rodrigues/?published=t

![Service Discovery POC](Service%20Discovery%20POC.drawio.png)

These projects illustrate the service discovery & client load balance with microservices inter dependent.
- Components
  - eureka-service: Netflix OSS Service discovery 
  - region-service: microservice exposing region services. This microservice depends on country-service
  - country-service: microservice exposing country services
- Features
  - Spring Boot: Spring Boot application
  - Eureka: service discovery  
  - Ribbon: client side load balance
  - Feign: declarative rest client 
  - Hystrix: circuit breaker implementation
  - H2: In Memory database
  - Spring Data JPA: declarative JPA implementation   

## Eureka Server
Default Eureka Server which runs on `port 8761`

## Region Service
This microservice run on `port 8080` and expose the following service:
````shell
  /region                     GET find all regions
  /region/{id}                GET find region by id
  /region/{id}/countries      GET find region and its countries, by region id
````

Below is the ribbon/feign client acting as country-service proxy 
````java
@RibbonClient("country-service")
@FeignClient("country-service")
public interface CountryServiceProxy {

    @GetMapping("country/region/{id}")
    List<CountryDTO> findByRegionId(@PathVariable("id") Integer id);
}
````
And here, the hystrix fallback defined for `findByRegionId` (countries by region id).
When the service is down, the fallback method will return an empty country list
````java
@Service
public class CountryService {
    
  // omitted
  
  @HystrixCommand(fallbackMethod= "findByRegionIdFallback")
  public List<CountryDTO> findByRegionId(Integer id) {
    return countryServiceProxy.findByRegionId(id);
  }

  public List<CountryDTO>  findByRegionIdFallback(Integer id, Throwable throwable){
    log.error("calling fallback..", throwable);
    return List.of(new CountryDTO());
  }
}

````
## Country Service
This microservice run on `ports 8081-8082` and expose the following services:
````shell
  /country                    GET find all countries
  /country/{id}               GET find country by id
  /country/region/{id}        GET find countries by region id (region-service dependent)
````

# Build & Run with Docker
````shell
  $ cd service-discovery
  $ mvn clean pakage
  $ docker-compose up -d --build --scale country-service=2
  $ docker-compose ps
````

# Test
- Check client registration on Eureka Server: `http://localhost:8761`
- Access `http://localhost:8080/region/1/countries`
- Check logs from country-service instance #1 and #2 and see the request being balanced
````shell
  $ docker ps -a --filter="name=country"
  $ docker logs <container id>
````
- Scale down country service to run one instance only
````shell
  $ docker-compose up -d --build --scale country-service=1
  $ docker-compose ps
````
- Access `http://localhost:8080/region/1/countries`

# Environment
````shell
  $ uname -mprs
  Darwin 20.6.0 arm64 arm
  
  $ docker -v
  Docker version 20.10.11, build dea9396
  
  $ docker-compose -v
  docker-compose version 1.29.2, build 5becea4c
  
  $ java -version
  java version "11.0.13" 2021-10-19 LTS
  Java(TM) SE Runtime Environment 18.9 (build 11.0.13+10-LTS-370)
  Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.13+10-LTS-370, mixed mode)
````
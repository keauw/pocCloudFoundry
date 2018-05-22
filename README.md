Check out project from GIT repository 

Run "mvn clean install" at parent pom.xml 's location

 

Steps to start applications locally by run as spring boot 
registry-server >> config-server >> service-engine and service-wheel  >> circuit-breaker-gateway >> car-portal

App monitorying / swagger-ui

registry-server       >>  http://localhost:8761/
config-server        >>  http://localhost:8888/v2/api-docs  , http://localhost:8888/application-local.yml
service-engine      >>  http://localhost:8181/swagger-ui.html
service-wheel       >>  http://localhost:8182/swagger-ui.html
circuit-breaker-gateway >>  http://localhost:8886/swagger-ui.html  , http://localhost:8886/hystrix  to open dashboard and then put http://localhost:8886/hystrix.stream to start monitoring
car-portal             >> http://localhost:8180/swagger-ui.html
 

Steps to push applications to ADP
Create user variable NO_PROXY : th.adp.allianz,.th.adp.allianz

Login to https://login.run.pivotal.io
Download cf-client  from this page https://login.run.pivotal.io/tools

After install cf-client then open cmd  and login with command  "cf login -a api.run.pivotal.io"

 

Steps to push applications

After login , go to each project directory below and follow provided command .

pocCloudFoundryCloudRegistry > cf push
pocCloudFoundryCloudRegistry > cf create-user-provided-service eureka -p '{\"uri\":\"https://registry-server.{DOMAIN}\"}'
pocCloudFoundryCloudConfig > cf push
pocCloudFoundryCloudConfig > cf create-user-provided-service configserver -p '{\"uri\":\"https://config-server.{DOMAIN}\"}'
pocCloudFoundryCarServiceEngine > cf push
pocCloudFoundryCarServiceWheel > cf push
pocCloudFoundryCloudCircuitBreaker > cf push
pocCloudFoundryCarPortal > cf push

 

App monitorying / swagger-ui

registry-server       >>  https://registry-server.{DOMAIN}/
config-server        >>  https://config-server.{DOMAIN}/v2/api-docs  ,    https://config-server.{DOMAIN}/application-cloud.yml
service-engine      >>  https://service-engine.{DOMAIN}/swagger-ui.html
service-wheel       >>  https://service-wheel.{DOMAIN}/swagger-ui.html
circuit-breaker-gateway >>  https://circuit-breaker-gateway.{DOMAIN}/swagger-ui.html  , https://circuit-breaker-gateway.{DOMAIN}/hystrix  to open dashboard and then put https://circuit-breaker-gateway.{DOMAIN}/hystrix.stream to start monitoring
car-portal             >> https://car-portal.{DOMAIN}/swagger-ui.html
 

 

Add syslog to each application

> cf cups syslog -l syslog-tls://logs5.papertrailapp.com:11529  
> cf bind-service registry-server syslog
> cf restart registry-server

 

 ![alt text](https://github.com/keauw/pocCloudFoundry/blob/master/CFDemoDiagram.png)

 


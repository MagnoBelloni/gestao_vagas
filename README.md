## How to Run

### Containers
- Put the `application-example.properties` in the `application.properties`
- $ `docker-compose up -d`
  - this command will pull and start the following containers:
    - Postgres
    - Sonarqube
    - Prometheus
    - Grafana
- $ `mvn spring-boot:run`

## Sonarqube

### Access
- To access sonarqube page you should access: http://localhost:9000
- With the credentials:
  - admin
  - admin

### First access and creation
- Manually
- Project display name: gestao_vagas
- Project key: gestao_vagas
- Main branch name: main
- Locally
- Expires in: 90 days
- Copy the token
- Maven
- Copy the generated script
- Run the script in your favorite terminal

### How to create a quality gate

Quality Gate is the conditions on how to check if your code is good

- Click on Quality Gates
- create
- Set a name
- Unlock editing
- Add condition
- Overall Code for all the code/New Code for only new code based on your policits
- Select coverage for the coverage of your code put a value and click save
- Projects
- All
- Select the project
- Reload the page
- Rerun the script
- Check your project page


## Swagger
The api documentation
- The swagger page is on the path: /swagger-ui/index.html

## Actuator
Metrics of the application, like healthcheck, memory used, infos... 
- The actuator page is on the path: /actuator

## Prometheus
Monitoring system and alerts events
- The Prometheus page is on the path: localhost:9090

## Grafana
Observability platform
- The Grafana page is on the path: localhost:3000
- The default user is admin:admin

### How to connect Grafana with Prometheus
- Connections
- Prometheus
- Add new datasource
- In the field prometheus server url in the section 'Connection' put: http://gestao_vagas_prometheus:9090
  - the name 'gestao_vagas_prometheus' can be replaced by the same container_name as in the docker-compose file, it can be accessed because they are in the same network
- Save & Test

### How to import Micrometer/Any Dashboard
- Dashboards
- New
- Import
- Put the Id 4701(Micrometer)
  - You can see the Ids in the Grafana website in the dashboards page
- Load
- Select your prometheus source
- Import

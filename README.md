# HealthSys-Computacao-Distribuida

### Pré-requisitos (executar uma vez)

## Criar a rede Docker para os serviços se conectar
```bash
docker network create healthsys-internal
```

## Criar um arquivo `.env` na raiz do projeto com as seguintes variáveis de ambiente:

```bash
# Database / common
DB_NAME=db
DB_USERNAME=Nome_Usuario_DB
DB_PASSWORD=Senha_DB

MONGO_USER=Nome_Usuario_Mongo
MONGO_PASSWORD=Senha_Mongo
MONGO_INITDB_ROOT_USERNAME=Usuario
MONGO_INITDB_ROOT_PASSWORD=Senha

# Application ports
API_GATEWAY_PORT=8080
AUTH_SERVICE_PORT=8081
PACIENTE_SERVICE_PORT=8082
TRIAGEM_SERVICE_PORT=8083
NOTIFICATION_SERVICE_PORT=8084
MEDICAL_RECORDS_SERVICE_PORT=8085
BED_SERVICE_PORT=8086
SERVICE_DISCOVERY_PORT=8761

# Database host ports for local access
AUTH_SERVICE_DB_PORT=5001
PACIENTE_SERVICE_DB_PORT=5002
TRIAGEM_SERVICE_DB_PORT=5003
BED_SERVICE_DB_PORT=5004
MEDICAL_RECORDS_SERVICE_DB_PORT=5005
NOTIFICATION_SERVICE_DB_PORT=5672
RABBITMQ_AMQP_PORT=5672

# RabbitMQ management
RABBITMQ_MANAGEMENT_PORT=15672
RABBITMQ_USER=Usuario_RABBITMQ
RABBITMQ_PASSWORD=Senha_RABBITMQ
SERVICE_DISCOVERY_PORT=8761
JWT_SECRET=TokenJWT

# JPA
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true

# Docker network name
DOCKER_NETWORK=healthsys-internal

# Service-specific database names
BED_DB_NAME=bed_db
MEDICAL_RECORDS_DB_NAME=medical_db

# Eureka / service discovery
EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=http://service-discovery:8761/eureka/

# Observability
PROMETHEUS_PORT=9090
GRAFANA_PORT=3000
GRAFANA_ADMIN_USER=SEU_USUARIO_GRAFANA
GRAFANA_ADMIN_PASSWORD=SUA_SENHA_GRAFANA

# Redis
REDIS_USER=Usuario_Redis
REDIS_HOST=redis
REDIS_PORT=6379
REDIS_PASSWORD=Senha_Redis
REDIS_TIME_TO_LIVE=600000
```

**OBS: Substitua os valores pelos valores reais do seu ambiente. E lembre-se de verificar se a .env está no gitignore e não commitado**

Se a rede já existir, o Docker vai retornar erro de rede duplicada e você pode ignorar.

# 2) Comandos

## Construção das imagens

### Construir as imagens dos serviços individualmente:
```bash
docker compose build auth-service
docker compose build paciente-service
docker compose build triagem-service
docker compose build notification-service
docker compose build api-gateway
docker compose build service-discovery
docker compose build bed-service
docker compose build medical-records-service
docker compose build prometheus
docker compose build grafana
docker compose build redis
```

### Construir as imagens de todos os serviços:
```bash
docker compose build
```

## Subir os serviços

### Subir os serviços específicos:
```bash
docker compose up -d auth-service
docker compose up -d paciente-service
docker compose up -d triagem-service
docker compose up -d notification-service
docker compose up -d api-gateway
docker compose up -d service-discovery
docker compose up -d bed-service
docker compose up -d medical-records-service
docker compose up -d prometheus
docker compose up -d grafana
docker compose up -d redis
```

### Subir todos os serviços:
```bash
docker compose up -d
```

## Subir os serviços aplicando novas mudanças

### Subir serviços específicos enquanto aplica novas mudanças:
```bash
docker compose up -d --build auth-service
docker compose up -d --build paciente-service
docker compose up -d --build triagem-service
docker compose up -d --build notification-service
docker compose up -d --build api-gateway
docker compose up -d --build service-discovery
docker compose up -d --build bed-service
docker compose up -d --build medical-records-service
docker compose up -d --build prometheus
docker compose up -d --build grafana
docker compose up -d --build redis
```

### Subir apenas o Observability (Prometheus + Grafana):
```bash
docker compose up -d --build prometheus grafana
```

### Alternativa de execução do Observability:
```bash
docker compose up -d --build grafana prometheus
```

### Subir o stack completo com observability:
```bash
docker compose up -d --build service-discovery api-gateway auth-service paciente-service triagem-service notification-service bed-service medical-records-service prometheus grafana redis
```

### Subir todos os serviços enquanto aplica novas mudanças:
```bash
docker compose up -d --build
```

> Observação: `observability` não é um serviço do compose raiz; no compose principal ele entra via `include`. Para subir Grafana e Prometheus, use `prometheus grafana`.

## Ver logs

```bash
docker compose logs auth-service
docker compose logs paciente-service
docker compose logs triagem-service
docker compose logs notification-service
docker compose logs api-gateway
docker compose logs service-discovery
docker compose logs bed-service
docker compose logs medical-records-service
docker compose logs prometheus
docker compose logs grafana
docker compose logs redis
```

## Parar os serviços:

### Parar serviços específicos:
```bash
docker compose down auth-service
docker compose down paciente-service
docker compose down triagem-service
docker compose down notification-service
docker compose down api-gateway
docker compose down service-discovery
docker compose down bed-service
docker compose down medical-records-service
docker compose down prometheus
docker compose down grafana
docker compose down redis
```

### Para parar todos os serviços, utilize:
```bash
docker compose down
```

## Parar e limpar dados de serviços:

### Parar e limpar dados de serviços específicos:
```bash
docker compose down -v auth-service
docker compose down -v paciente-service
docker compose down -v triagem-service
docker compose down -v notification-service
docker compose down -v api-gateway
docker compose down -v service-discovery
docker compose down -v bed-service
docker compose down -v medical-records-service
docker compose down -v prometheus
docker compose down -v grafana
docker compose down -v redis
```
### Para parar e limpar dados de todos os serviços, utilize:
```bash
docker compose down -v
```

### Testar Redis
```bash
docker compose exec -it redis redis-cli
auth Usuario_Redis Senha_Redis
ping
```

### Checar keys cacheadas no Redis
```bash
docker compose exec -it redis redis-cli
auth Usuario_Redis Senha_Redis
keys *
```

## Portas padrão

- API Gateway: `8080`
- Auth Service: `8081`
- Paciente Service: `8082`
- Triagem Service: `8083`
- Notification Service: `8084`
- Service Discovery (Eureka): `8761`
- Prometheus: `9090`
- Grafana: `3000`
- Auth DB (Postgres): `5001`
- Paciente DB (Postgres): `5002`
- Triagem DB (Postgres): `5003`
- Bed Service: `8086`
- Medical Records Service: `8085`
- Bed DB (Postgres): `5004`
- Medical Records DB (MongoDB): `5005`
- RabbitMQ AMQP: `5672`
- RabbitMQ Management: `15672`
- Redis: `6379`
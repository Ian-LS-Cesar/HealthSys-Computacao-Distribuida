# HealthSys-Computacao-Distribuida

### 1) Pré-requisitos (executar uma vez)

## 1.1) Criar a rede Docker para os serviços se conectar
```bash
docker network create healthsys-internal
```

## 1.2) Criar um arquivo .env na raiz do projeto com as seguintes variáveis de ambiente:

```bash
DB_NAME=db
DB_USERNAME=Nome_Usuario_DB
DB_PASSWORD=Senha_DB
API_GATEWAY_PORT=8080
AUTH_SERVICE_PORT=8081
AUTH_SERVICE_DB_PORT=5001
PACIENTE_SERVICE_PORT=8082
PACIENTE_SERVICE_DB_PORT=5002
TRIAGEM_SERVICE_PORT=8083
TRIAGEM_SERVICE_DB_PORT=5003
NOTIFICATION_SERVICE_PORT=8084
NOTIFICATION_SERVICE_DB_PORT=5672
RABBITMQ_MANAGEMENT_PORT=15672
RABBITMQ_USER=Usuario_RABBITMQ
RABBITMQ_PASSWORD=Senha_RABBITMQ
SERVICE_DISCOVERY_PORT=8761
NOTIFICATION_SERVICE_DB_PORT=5672
JWT_SECRET=TokenJWT
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
DOCKER_NETWORK=healthsys-internal
EUREKA_HOST=service-discovery
EUREKA_PORT=8167
```

**OBS: Substitua os valores pelos valores reais do seu ambiente. E lembre-se de verificar se a .env está no gitignore e não commitado**

Se a rede já existir, o Docker vai retornar erro de rede duplicada e você pode ignorar.

# 2) Comandos

## Construção das imagens dos serviços:
### Construir as imagens dos serviços:
```bash
docker compose build auth-service
docker compose build paciente-service
docker compose build triagem-service
docker compose build notification-service
docker compose build api-gateway
docker compose build service-discovery
```

### Construir as imagens de todos os serviços:
```bash
docker compose build
```

## Subir os serviços:

### Subir os serviços específicos:
```bash
docker compose up -d auth-service
docker compose up -d paciente-service
docker compose up -d triagem-service
docker compose up -d notification-service
docker compose up -d api-gateway
docker compose up -d service-discovery
```

### Subir todos os serviços:
```bash
docker compose up -d
```

## Subir os serviços aplicando novas mudanças:

### Subir serviços específicos enquanto aplica novas mudanças:
```bash
docker compose up -d --build auth-service
docker compose up -d --build paciente-service
docker compose up -d --build triagem-service
docker compose up -d --build notification-service
docker compose up -d --build api-gateway
docker compose up -d --build service-discovery
```

### Subir todos os serviços enquanto aplica novas mudanças:
```bash
docker compose up -d --build
```

##  Ver logs

```bash
docker compose logs auth-service
docker compose logs paciente-service
docker compose logs triagem-service
docker compose logs notification-service
docker compose logs api-gateway
docker compose logs service-discovery
```

```bash
docker compose logs
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
```
### Para parar e limpar dados de todos os serviços, utilize:
```bash
docker compose down -v
```

## Portas padrao

- API Gateway: `8080`
- Auth Service: `8081`
- Paciente Service: `8082`
- Triagem Service: `8083`
- Notification Service: `8084`
- Service Discovery (Eureka): `8761`
- Auth DB (Postgres): `5001`
- Paciente DB (Postgres): `5002`
- Triagem DB (Postgres): `5003`
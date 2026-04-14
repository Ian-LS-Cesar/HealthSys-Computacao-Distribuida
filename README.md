# HealthSys-Computacao-Distribuida

## Rodando os servicos pelo diretorio raiz

Os tres `docker-compose.yml` usam a rede externa `healthsys-internal`.

### 1) Pre-requisito (executar uma vez)

```bash
docker network create healthsys-internal
```

Se a rede ja existir, o Docker vai retornar erro de rede duplicada e voce pode ignorar.

### 2) Subir os servicos (sem entrar nas pastas)

Execute os comandos abaixo na raiz do projeto:

```bash
docker compose -f auth-service/docker-compose.yml up -d --build
docker compose -f paciente-service/docker-compose.yml up -d --build
docker compose -f api-gateway/docker-compose.yml up -d --build
```

### 3) Ver logs

```bash
docker compose -f auth-service/docker-compose.yml logs -f
docker compose -f paciente-service/docker-compose.yml logs -f
docker compose -f api-gateway/docker-compose.yml logs -f
```

### 4) Parar os servicos

```bash
docker compose -f api-gateway/docker-compose.yml down
docker compose -f paciente-service/docker-compose.yml down
docker compose -f auth-service/docker-compose.yml down
```

## Portas padrao

- API Gateway: `8080`
- Auth Service: `8081`
- Paciente Service: `8082`
- Auth DB (Postgres): `5001`
- Paciente DB (Postgres): `5002`

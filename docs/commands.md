# Quick Start Guide - Consul Service Discovery

## Полезные команды

```bash
docker-compose up -d consul
```


```bash
# Список всех сервисов
curl http://localhost:8500/v1/catalog/services
```

```bash
# Детали конкретного сервиса
curl http://localhost:8500/v1/catalog/service/product-service
```

```bash
# Health check сервиса
curl http://localhost:8500/v1/health/service/product-service
```

# Изменения в WildFly 27.0.1.Final для ebay-ejb

## Применённые изменения

### 1. **bin/standalone.conf** - Системные свойства

Добавлены строки (после строки 66):

```bash
# ============================================================================
# Configuration for ebay-ejb service
# ============================================================================
# Product service URL for ebay-ejb
JAVA_OPTS="$JAVA_OPTS -Dproduct.service.url=http://localhost:8080"
```

**Назначение**: Указывает URL сервиса продуктов, к которому обращается ebay-ejb.

**Изменение**: Если Product Service работает на другом хосте/порту, измените этот URL.

---

## Конфигурация EJB Pool

```xml
<subsystem xmlns="urn:jboss:domain:ejb3:10.0">
    <!-- ... -->
    <pools>
        <bean-instance-pools>
            <strict-max-pool name="slsb-strict-max-pool" max-pool-size="20" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
        </bean-instance-pools>
    </pools>
    <!-- ... -->
</subsystem>
```

И атрибут:
```xml
<session-bean>
    <stateless>
        <bean-instance-pool-ref pool-name="slsb-strict-max-pool"/>
    </stateless>
</session-bean>
```

## Параметры для настройки

### Изменение URL Product Service

Файл: `bin/standalone.conf`

```bash
# Вместо localhost:8080
JAVA_OPTS="$JAVA_OPTS -Dproduct.service.url=http://your-host:8080"
```

### Изменение размера EJB Pool

```bash
./bin/jboss-cli.sh --connect

/subsystem=ejb3/strict-max-bean-instance-pool=slsb-strict-max-pool:write-attribute(name=max-pool-size, value=50)
:reload
```

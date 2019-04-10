# hosts


## Prerequisites

```bash
docker run -d --name hostsDB -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=hosting -p 5434:5432 postgres:latest
```

## Run application in Docker

```bash
docker run --name hosting \
    -p 8080:8080 \
    -e KUMULUZEE_SERVER_BASEURL=http://192.168.19.10:8080 \
    -e KUMULUZEE_SERVER_HTTP_PORT=8080 \
    -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://192.168.19.10:5434/hosting \
    -e KUMULUZEE_CONFIG_ETCD_HOSTS=http://192.168.19.10:2379 \
    -e KUMULUZEE_DISCOVERY_ETCD_HOSTS=http://192.168.19.10:2379 \
    dkostadinovski/hosts
```



## API rest endpoints

@GET /v1/hosts/<user_id> // Temporary - returns User with ID=user_id (for Discovery service)

# users


## Prerequisites

```bash
docker run -d --name usersDB -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=users -p 5433:5432 postgres:latest
```

## Run application in Docker

```bash
docker build -t users .
docker run --name users -p 8080:8081 users
```

```bash
docker run --name users \
    -p 8082:8081 \
    -e KUMULUZEE_SERVER_BASEURL=http://192.168.19.10:8082 \
    -e KUMULUZEE_SERVER_HTTP_PORT=8081 \
    -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://192.168.19.10:5433/users \
    -e KUMULUZEE_CONFIG_ETCD_HOSTS=http://192.168.19.10:2379 \
    -e KUMULUZEE_DISCOVERY_ETCD_HOSTS=http://192.168.19.10:2379 \
    tvenko/users
```



## API rest endpoints

@GET /v1/users -> get list of all users <br />
@GET /v1/users/<id> -> get user with id <br />
@POST /v1/users -> create new user <br />
    body params: <br />
      -username <br />
      -email <br />
      -passwd <br />
      -firstname <br />
      -lastname <br />
@DELETE /v1/users/<id> -> delete user with id <br />
@PUT /v1/users/id -> update user with id <br />
    body params: same as @POST <br />

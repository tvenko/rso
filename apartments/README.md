# apartments


## Prerequisites

```bash
docker run -d --name apartmentsDB -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=apartments -p 5433:5432 postgres:latest
```

## Run application in Docker

```bash
docker build -t apartments .
docker run --name apartments -p 8080:8081 apartments
```

## API rest endpoints

@GET /v1/apartments -> get list of all apartments <br />
@GET /v1/apartments/<id> -> get apartment with id <br />
@POST /v1/apartments -> create new apartment <br />
    body params: <br />
      -description <br />
      -location <br />
      -persons <br />
      -squareMeters <br />
@DELETE /v1/apartments/<id> -> delete user with id <br />
@PUT /v1/apartments/id -> update apartment with id <br />
    body params: same as @POST <br />
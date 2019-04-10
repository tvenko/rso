# user-analysis
Analysis of users
## Run application in Docker


```bash
docker run --name user-analysis \
    -p 8089:8091\
    -e KUMULUZEE_SERVER_BASEURL=http://localhost:8091 \
    -e KUMULUZEE_SERVER_HTTP_PORT=8091 \
    bsircelj/user-analysis
```

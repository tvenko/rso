# apartments-analysis
Analysis of apartments
## Run application in Docker


```bash
docker run --name apartments-analysis \
    -p 8089:8089 \
    -e KUMULUZEE_SERVER_BASEURL=http://localhost:8089 \
    -e KUMULUZEE_SERVER_HTTP_PORT=8089 \
    bsircelj/apartments-analysis
```

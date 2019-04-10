# hosting-analysis

## Run application in Docker

```bash
docker run --name hosting-analysis \
    -p 8087:8087 \
    -e KUMULUZEE_SERVER_BASEURL=http://localhost:8087 \
    -e KUMULUZEE_SERVER_HTTP_PORT=8087 \
    dkostadinovski/hosting-analysis
```

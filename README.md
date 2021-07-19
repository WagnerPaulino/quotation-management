# Quotation Management

* How to execute the stock-manager
```
$ docker run -p 8080:8080 --network <yourNetwork> --name stock-manager -d lucasvilela/stock-manager
```

* How to execute the quotation management

Using maven to build the jar file
```bash
$ docker build -t quotation-management -f Dockerfile-maven .
```
Or
```
$ mvn clean install && docker build -t quotation-management -f Dockerfile-jar .
```
Running the container
```bash
$ docker run -p 8081:8081 --network <yourNetwork> --name quotation-management quotation-management
```

* how to call the endpoints
```bash
$ curl --location --request POST 'http://localhost:8081/quotations/' --header 'Content-Type: application/json' \
--data-raw '{
    "stockId": "vale5",
    "quotes": {
        "2021-12-27": 12.50
    }
}'
```
---
**NOTE**

The quotation management service depends on stock manager and mysql version 8 to work

---

* See the swagger documentation here `http://localhost:8081/swagger-ui/#/`
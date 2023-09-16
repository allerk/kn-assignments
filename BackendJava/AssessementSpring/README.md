# Getting Started

## About:
This is Spring Boot based order management system api.


## How to run?
[//]: # (- Open gradle -> gretty -> appRun or simply ```./gradlew appStart``` or by running junit tests &#40;i.e test -> java -> ee.quiz&#41;)
[//]: # (- Build a project using gradle: ```./gradlew clean build```)
- Use ```docker compose up``` to create db
- Run either manually ```./gradlew bootRun``` or by running main file
- By running junit tests (i.e test -> java -> ee.quiz)
## Features
- There is no auto-clear db function!!! To be able to make db empty need to drop db manually
- There is only POST/GET Rest Api methods in each controller according to requirements (NO UPDATE/DELETE)

### BENEFICIAL FEATURES (TO GET MORE POINTS)
- [x] **Search Orders by product	(using both JPQL and Criteria/Specification queries)** - /api/orders/productId/jpql/{id}
- [x] **Search Orders by customer (using both JPQL and Criteria/Specification queries)** - /api/orders/productId/criteria/{id}
- [x] **Setup Liquibase for database generation/updates** - resources/db.changelog/changes
- [x] **Change quantity of products in an order line** - created in between table (OrderLineProduct) to avoid Many-to-Many relationship. By adding new data to this table, quantity field will automatically increment in OrderLine table.

### TEST DATA
- [x] **You can generate test data to insert in DB with Mockaroo tool** - Mockaroo was used to generate random data and exported file now in test/resources/mockaroo. No interaction with api :(
- [x] **Unit test will give you some additional points** - There is 1 unit test (CreateProductServiceTest) to test service/repository.
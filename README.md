# 3pa-register-api
The Third Party Agent Register application is an internal service.
This service is responsible for collecting third party agent details and the issuance of an agent assurance code.

The service integrates with a number of internal systems. 

:point_up: This was largely an investigation into what spring-data-rest can provide and we are aware it doesn't fully match the design specification.

Requirements
------------
* Java 11
* [Git](https://git-scm.com/downloads)
* [Maven](https://maven.apache.org/download.cgi)
* [3pa-register-api](https://github.com/companieshouse/3pa-register-api)
* Internal Companies House core services
* [CHS Docker](https://github.com/companieshouse/docker-chs-development) for mongoDB, CDN, and co-ordination with 3pa-register-web

## Building and Running Locally

### Docker
- Clone Docker CHS Development and follow the steps in the README.
- check out `on-hold/take-snapshot-of-wip` branch 
- module enable `roe-3pa`
- if you want to make changes, development enable `3pa-register-api`

### Configuration
These were added but not used as yet:

|Variable|Description|
|---|---|
|`REF_PATTERN`| Layout of generated Assurance code (# is placeholder for random symbol)|
|`REF_SYMBOL_SET`| Set of letters / digits to be used for Assurance code|
|`RNG_ALGORITHM_NAME`| Random Number Generator algorithm (not hardcoded in the soure for security reasons)|
|`RNG_PROVIDER_NAME`| Provider name of the RNG algorithm (not hardcoded in the soure for security reasons)|

### end-2-end environments
- Concourse pipelines build and deploy this service

## Usage

- designed for use by `3pa-register-admin-web`
  - To use spring-data-rest, accept headers need to be added to the Post request from the sdk.  Otherwise spring-data-rest will return an empty request body which the sdk will not be able to process.
  - the following sdk branches also need to be installed
    - https://github.com/companieshouse/private-api-sdk-java/tree/feature/add-roe-3pa
    - https://github.com/companieshouse/api-sdk-java/tree/feature/add-accept-header-to-port-request

- directly eg via Postman
  - we are using the standard config for [spring-data-rest resources](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources)
  - :warning: because the `MANAGEMENT_ENDPOINTS_WEB_BASE_PATH` is the same as the `spring.data.rest.basePath` the healthcheck endpoint is obscuring the spring data resources endpoints.  This design could be made more discoverable if these urls were separate.
  - the response from GET `http://api.chs.local/third-party-agencies` should be:
  ```
    "_links": {
        "assuranceCodes": {
            "href": "http://localhost:8080/third-party-agencies/codes{?page,size,sort}",
            "templated": true
        },
        "thirdPartyAgents": {
            "href": "http://localhost:8080/third-party-agencies/3pas{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/third-party-agencies/profile"
        }
    }
  ```
  - the resources made available by spring-data-rest are:
    - Discovered with:
      - GET `http://api.chs.local/third-party-agencies/profile`
### thirdPartyAgents
- discover the operations with
  - GET `http://api.chs.local/third-party-agencies/profile/3pas`
  - these include:
    - GET, POST, PUT, DELETE and PATCH on `http://api.chs.local/third-party-agencies/3pas`
- discover the data schema with
  - GET `http://api.chs.local/third-party-agencies/profile/3pas` using Accept header of `application/schema+json`
### assuranceCodes
- discover the operations with
  - GET `http://api.chs.local/third-party-agencies/profile/codes`
  - these include:
    - GET, POST, PUT, DELETE and PATCH on `http://api.chs.local/third-party-agencies/codes`
- discover the data schema with
  - GET `http://api.chs.local/third-party-agencies/profile/codes` using Accept header of `application/schema+json`
### healthcheck 
 - `http://api.chs.local/third-party-agencies/healthcheck`


## Design
- refer to Confluence pages
- [specification](https://developer-specs.cidev.aws.chdev.org/third-party-agents-register-api/reference)

## Typical scenario
With internal app privileges API_KEY in the Authorization header..
### 1. add 3pa details
POST `http://api.chs.local/third-party-agencies/3pas`
with body:
  ```
  {
      "agentName": "Thundercats",
      "contact_name": "Tim Tom",
      "email": "a@b.com",
      "address": {
          "address_line_1": "1",
          "address_line_2": "Justice Road",
          "town": "Cardiff",
          "county": "NYS",
          "country": "Wales",
          "postcode": "111JJ"
      }
  }
  ```
### 2. add assuranceCode
POST `http://api.chs.local/third-party-agencies/codes`
with body:
  ```
  {
      "code": "123",
      "startDate": "2022-07-08T00:00:00Z"
  }
  ```
### 3. patch new assuranceCode to 3pa
PATCH `http://api.chs.local/third-party-agencies/3pas/<<3pa_id from self link in step 1>>`
with body:
```
{
    "codes": [
        <<self link from step 2>>"
    ]
}
```
### 4. fetch the 3pa codes
GET `http://api.chs.local/third-party-agencies/3pas/<<3pa_id from self link in step 1>>/codes`
for example:
```
{
    "_embedded": {
        "assuranceCodes": [
            {
                "code": "123",
                "startDate": "2022-07-08T00:00:00Z",
                "endDate": null,
                "_links": {
                    "self": {
                        "href": "http://api.chs.local/third-party-agencies/codes/62d6bfdbc4f1d449a191fed7"
                    },
                    "assuranceCode": {
                        "href": "http://api.chs.local/third-party-agencies/codes/62d6bfdbc4f1d449a191fed7"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://api.chs.local/third-party-agencies/3pas/62d6bd6ac4f1d449a191fed5/codes"
        }
    }
}
```

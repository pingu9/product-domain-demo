# 상품, 브랜드, 카테고리 관련 서버 데모

## 목차
- [구현 범위 및 가정](#구현-범위-및-가정)
- [버전](#버전)
- [빌드 및 실행](#빌드-및-실행)
- [단위, 통합 테스트 실행](#단위-통합-테스트-실행)
- [API 명세](#api-명세)

---

## 구현 범위 및 가정

구현 범위는 다음과 같습니다:
- 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API(아래 API 명세 7번)
- 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API(아래 API 명세 8번)
- 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API(아래 API 명세 9번)
- 브랜드, 상품 생성, 수정, 삭제 API(아래 API 명세 1~6번)
- 각 주요 기능에 대한 단위, 통합 테스트

또한 다음과 같은 가정을 두고 구현하였습니다:
- 상품은 Product, 브랜드는 Brand, 카테고리는 Category로 표현합니다.
- 상품 - 브랜드와 상품 - 카테고리는 모두 ManyToMany 관계를 가지고 있다고 가정하고 각각의 Join Table을 ProductBrand, ProductCategory로 정의합니다.
- 이러한 가정이 있을 때 카테고리별 최저가격 브랜드, 총합 최저가격 브랜드는 여러 개가 있을 수 있습니다. 문제 예시와 응답을 맞추기 위해 이 경우에는 이름이 알파벳 순으로 뒤에 있는 경우를 우선시해서 표시합니다.

### 버전
- Java 17
- Spring Boot 3.3.5

### 빌드 및 실행

- 아래 명령어를 순차적으로 실행합니다.

  - ./gradlew clean build
  - ./gradlew bootRun

서버가 실행한 이후 Swagger를 통하여 API 호출을 테스트할 수 있습니다.
http://localhost:8080/swagger-ui/index.html

### 단위, 통합 테스트 실행

- ./gradlew test

## API 명세

#### 1. 브랜드 생성
- **URL**: `POST /brands`
- **설명**: 새로운 브랜드를 생성합니다.
- **요청 본문**:
  ```json
  {
    "name": "브랜드 이름"
  }
  ```
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. body에 name이 입력되지 않은 경우
    2. 브랜드 이름이 중복될 경우
      

#### 2. 브랜드 수정
- **URL**: `PUT /brands`
- **설명**: 기존 브랜드 정보를 수정합니다.
- **요청 본문**:
  ```json
  {
    "id": 1,
    "name": "브랜드 이름"
  }
  ```
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. 브랜드에 id, name이 입력되지 않았을 경우
    2. 해당 id를 가진 브랜드가 없을 경우
    3. 변경하려는 브랜드 이름이 중복될 경우

#### 3. 브랜드 삭제
- **URL**: `DELETE /brands/{id}`
- **설명**: 특정 브랜드를 삭제합니다.
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. 해당 id를 가진 브랜드가 없을 경우
    
#### 4. 상품 생성
- **URL**: `POST /products`
- **설명**: 새로운 상품을 생성합니다.
- **요청 본문**:
  ```json
  {
    "name": "상품 이름",
    "price": 10000,
    "brandIds": [1, 2],
    "categoryIds": [1,2]
  }
  ```
- **성공 시 응답**:
- **상태 코드**: 200
- **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. body에 name, price, brandIds, categoryIds가 입력되지 않은 경우
    2. 해당 id를 가진 브랜드가 없을 경우
    3. 해당 id를 가진 카테고리가 없을 경우
    4. 상품 이름이 중복될 경우

#### 5. 상품 수정
- **URL**: `PUT /products`
- **설명**: 기존 상품 정보를 수정합니다.
- **요청 본문**:
  ```json
  {
    "id": 1,
    "name": "상품 이름",
    "price": 10000,
    "brandIds": [1, 2],
    "categoryIds": [1,2]
  }
  ```
  
- **성공 시 응답**:
- **상태 코드**: 200
- **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. body에 id, name, price, brandIds, categoryIds가 입력되지 않은 경우
    2. 해당 id를 가진 상품이 없을 경우
    3. 해당 id를 가진 브랜드가 없을 경우
    4. 해당 id를 가진 카테고리가 없을 경우
    5. 변경하려는 상품 이름이 중복될 경우

#### 6. 상품 삭제
- **URL**: `DELETE /products/{id}`
- **설명**: 특정 상품을 삭제합니다.
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
    {}
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. 해당 id를 가진 상품이 없을 경우

#### 7. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회
- **URL**: `GET /products/price/min-price-by-all-categories`
- **설명**: 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회합니다.
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
  {
     "contents": [
       {
         "categoryId": 1,
         "categoryName": "상의",
         "brandName": "C",
         "price": 10000
       },
       {
         "categoryId": 2,
         "categoryName": "아우터",
         "brandName": "E",
         "price": 5000
       },
       {
         "categoryId": 3,
         "categoryName": "바지",
         "brandName": "D",
         "price": 3000
       },
       {
         "categoryId": 4,
         "categoryName": "스니커즈",
         "brandName": "A",
         "price": 9000
       },
       {
         "categoryId": 5,
         "categoryName": "가방",
         "brandName": "A",
         "price": 2000
       },
       {
         "categoryId": 6,
         "categoryName": "모자",
         "brandName": "D",
         "price": 1500
       },
       {
         "categoryId": 7,
         "categoryName": "양말",
         "brandName": "I",
         "price": 1700
       },
       {
         "categoryId": 8,
         "categoryName": "액세서리",
         "brandName": "F",
         "price": 1900
       }
    ],
    "totalPrice": 34100
  }
  ```
  - **실패 시 응답**:
    - 정의된 실패 케이스 없음

#### 8. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회
- **URL**: `GET /products/price/min-price-by-all-brands
  - **설명**: 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회합니다.
    - **성공 시 응답**:
      - **상태 코드**: 200
      - **응답 본문**:
      ```json
          {
              "최저가" : {
                "브랜드" : "D",
                "카테고리": [
                    {
                        "카테고리" : "상의",
                        "상품가격" : 10100
                    },
                    {
                        "카테고리" : "아우터",
                        "상품가격" : 5100
                    },
                    {
                        "카테고리" : "바지",
                        "상품가격" : 3000
                    },
                    {
                        "카테고리" : "스니커즈",
                        "상품가격" : 9500
                    },
                    {
                        "카테고리" : "가방",
                        "상품가격" : 2500
                    },
                    {
                        "카테고리" : "모자",
                        "상품가격" : 1500
                    },
                    {
                        "카테고리" : "양말",
                        "상품가격" : 2400
                    },
                    {
                        "카테고리" : "액세서리",
                        "상품가격" : 2000
                    }
                 ]
              },
              "총액" : 36100
          }
      ```
    - **실패 시 응답**:
      - 정의된 실패 케이스 없음

#### 9. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API

- **URL**: `GET /products/price/min-max-price-by-category-name/{categoryName}`
- **설명**: 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
- **성공 시 응답**:
  - **상태 코드**: 200
  - **응답 본문**:
  ```json
  {
    "카테고리": "아우터",
    "최저가": {
      "브랜드": "E",
      "가격": 5000
    },
    "최고가": {
      "브랜드": "F",
      "가격": 7200
    }
  }
  ```
  - **실패 시 응답**:
    - **상태 코드**: 400
    1. 해당 카테고리 이름이 없을 경우
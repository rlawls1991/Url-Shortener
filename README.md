# 과제 설명 및 해결 방안
## URL을 입력받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트하는 URL Shortening Service
### 예) https://en.wikipedia.org/wiki/URL_shortening => http://localhost/JZfOQNro
* URL 입력폼 제공 및 결과 출력
    * client(Html)와 RestApi(Server) 구현
    * validation으로 URL 검증
* URL Shortening Key는 8 Character 이내로 생성되어야 합니다.
    * Shortening Key길이가 8글자보다 초과할 경우 `ShortKeyOutLengthException` 발생
* 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 합니다.
    * 동일한 URL의 경우 DB에서 조회하여 반환
    * 동일하지 않은 경우 새로 생성
* 동일한 URL에 대한 요청 수 정보를 가져야 한다(화면 제공 필수 아님)
    * 동일한 ShortURL Redirect처리시 요청수 증가 
* Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.
    * Shortened URL Redirect 처리: http://localhost:8080/[Shortening Key] 개발
    * 조회시 저장된 URL이 없으면 `ShortUrlNotFoundException`발생 
      * 발생시 `shortUrlNotFoundPage` 페이지 이동
* Database 사용은 필수 아님
    * 동일한 URL 처리를 위해 H2( mode = MySQL) 사용
    
# RestApi 설명
### src > docs > asciidoc > index.adoc 을 보게 된다면 RestApi http-request, http-response의 결과 확인가능

# Test 종류
## 통합테스트
### UrlShortenerApiControllerTest
* createUrlShortener
  * 정상적인 ShortURL 생성
* createUrlShortener_Bad_Request
  * 입력 받을 수 없는 값을 사용한 경우에 에러가 발생하는 테스트
* createShortener_Bad_Request_Wrong_Input
  * URL의 형식이 맞지 않는경우 에러가 발생하는 테스트
  
## 단위테스트
### UrlValidationTest
* checkUrLValidation_TRUE
  * URL 형식이 맞는지
* checkUrLValidation_FALSE
  * URL 형식이 아닌지
### UrlEncoderTest
* checkSameValue
  * 같은 값을 파라미터로 주게 되면 같은 값이 나오는지

# 환경 설정
## Backend
| 이름                   | Version        | 
| :---                  | :---:          |
| Java                  | 11             |
| Spring Boot           | 2.4.3          |
| thymeleaf             | 2.4.3          |
| JUnit                 | JUnit 5        |
| validation            | 2.4.3          |
| jpa                   | 2.4.5          |
| lombok                | 1.18.18        |
| H2                    | 1.4.2          |
| querydsl              | 1.0.10         |
| restdocs              | 2.0.5.RELEASE  |
| hateoas               | 1.2.4          |

## Frontend
| 이름                   | Version        | 
| :---                  | :---:          |
| jQuery                | 3.6.0          |


# 환경 준비
### Java
* java 설치 여부 및 버전 확인
  ```console
  ]$ java -version
  openjdk version "-11.0.7.10-1"
  OpenJDK Runtime Environment (build -11.0.7.10-1)
  OpenJDK 64-Bit Server VM (build 25.201-b09, mixed mode)
  ```
* java 11 설치
  ```console
  ]$ sudo yum install java--11.0.7.10-1-openjdk-devel
  ```

### Gradle
* gradle 설치 여부 및 버전 확인
  ```console
  ]$ $ gradle -v
  ------------------------------------------------------------
  Gradle 6.2.2
  ------------------------------------------------------------
  ```
* gradle 설치
  ```console
  ]$ wget https://services.gradle.org/distributions/gradle-6.2.2-bin.zip -P /tmp
  ]$ mkdir /opt/gradle
  ]$ unzip -d /opt/gradle /tmp/gradle-6.2.2-bin.zip
  ]$ ls /opt/gradle/gradle-6.2.2
  bin  init.d  lib  LICENSE  NOTICE  README
  
  ]$ sudo vi /etc/profile.d/gradle.sh
  export GRADLE_HOME=/opt/gradle/gradle-6.2.2
  export PATH=${GRADLE_HOME}/bin:${PATH}
  
  ]$ sudo chmod +x /etc/profile.d/gradle.sh
  ]$ source /etc/profile.d/gradle.sh
  ]$ gradle -v
  Welcome to Gradle 6.2.2!
  ...
  ...
  ------------------------------------------------------------
  Gradle 6.2.2
  ------------------------------------------------------------
  ```


# 프로젝트 빌드 방법
### Java
* java
```
https://github.com/rlawls1991/url-shortener
```
## DB
```
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.hikari.jdbc-url=jdbc:h2:mem:testdb;MODE=MYSQL
spring.datasource.username=sa
spring.datasource.password=
```

# 빌드 및 실행방법
* build & test
  ```console
  ]$ cd PROJECT_HOME
  ]$ gradle build 
  ```

* build without test
  ```console
  ]$ cd PROJECT_HOME
  ]$ gradle build -x test 
  ```

* run project
  ```console
  ]$ cd PROJECT_HOME
  ]$ gradle run
  ```

* Service URL : http://localhost:8080
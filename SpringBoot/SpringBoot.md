## Docker MySql CLI

- `docker ps -a`
  - 도커의 모든 프로세스 확인
  - mysql 프로세스의 컨테이너 아이디를 복사한다.
- `docker exec -it [container id] bash`
  - 컨테이너 접속
- `mysql -u root -p`
  - mtsql root로 접속
  - 패스워드 입력
- `show databases`
  - 전체 데이터베이스 출력
- `use [database name]`
  - 데이터베이스 



## MySql CLI 명령어

- 데이터베이스 출력 `show databases`

- 데이터베이스 선택 `use [database name]`

- 테이블 출력 `show tables`

- 테이블 정보 출력 `desc [database name.table name]`

- 외래키 적용된 테이블 삭제

  ```
  SET foreign_key_checks = 0;
  DROP table [table name];
  SET foreign_key_checks = 1;
  ```



## Bean 검증

- `@NotNull`
  - null 허용 하지 않는다.
  - "" 허용한다.
- `@NotEmpty`
  - null 허용 하지 않는다.
  - ""허용하지 않는다.
  - " " (space) 허용한다.
- `@NotBlank`
  - 셋 다 허용하지 않는다.
- 실제 DB에는 모두 not null로 들어 간다.



## @column(nullable = false)

- `@NotNull` 은 유효성 검사를 위한 것
- `nullable = false` 는 데이터베이스 스키마 세부 사항
- `nullable = false`  을 설정하지 않으면 JPA는 null값을 허용한다. 따라서 null 객체 저장이 시도 될 때 DB로 보내게 되고 DB수준의 오류가 발생한다. `nullable = false`  를 설정하게 되면 JPA는 DB에 보내기 전에 예외를 던져 DB 로그 스팸을 방지하고 더 나은 오류를 제공한다.
- `nullable = false` 와 `@NotNull` 을 함께 사용하는 경우 어떤 객체의 어떤 필드가 거부되었는지 정확하게 확인 할 수 있는 데이터 구조를 제공한다.



## Spring boot JPA default value 설정

- 원시타입
  - 생성될때 JAVA 원시타입의 기본값이 설정되기 때문에 문제가 발생한다.
  - DTO에서 원시타입 선언시에 기본값을 넣는 방법
  - 어노테이션으로 해결하는 것은 나중에 알아볼 것



## @Builder

- class에 @Builder 어노테이션을 추가하면 빌더패턴을 사용할 수 있다. (생성자를 만들 필요가 없다.)
- lombok 1.16.16 버전 이상 부터는 @Builder.Default를 사용하여 필드의 기본값을 설정할 수 있다.
- 이전 버전에서는 기본 생성자(default constructor)를 만들어서 초기화 해줘야한다.
- `@NoArgsConstructor` 를 `@Builder`와  함께 사용하려면 `@AllArgsConstructor` 와 함께 사용하거나 손수 모든 필드를 가지는 생성자를 만들어 줘야한다. (`@Builder` 와 일부 필드만 가지는 생성자를 함께 써도 컴파일이 되지 않는다.)
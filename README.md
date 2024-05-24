# 프로젝트 개요
대출 이자율을 입력하였을때, 주식, 채권 정보를 토대로 얼마만큼의 수익을 낼수 있는지 확인해 볼수 있는 서비스.

해당 레포지토리는 사용자 요청이 왔을 경우 비즈니스 로직을 처리하는 레포지토리 입니다.

## 실행방법

1. docker 설치
```aidl
아래 홈페이지 에서 docker desktop 설치
https://www.docker.com/products/docker-desktop/
```

2. 프로젝트 실행
```aidl
$ ./gradlew bootJar
$ make up
```

3. docker desktop에 컨테이너가 잘 동작되었는지 확인


etc. docker container 말고 intellij에서 구동하고 싶을때
```aidl
1. docker container 에서 데이터 베이스를 제외한 애플리케이션 컨테이너 삭제
2. intellij 에서 run
3. 이후 테스트
```

주의사항
```aidl
db 데이터가 로컬에 저장되고, 초기 빌드 후 계속 해당 데이터를 참조하기 때문에
db 스키가마 변경되거나 하는 경우는 로컬 데이터를 삭제해야합니다.
$ make down
$ make up
```
```aidl
애플리케이션도 마찬가지로 이미지를 빌드 한뒤 사용하는 것이기 때문에,
bootJar 명령어로 jar 파일을 만든 뒤, 이미지를 빌드해서 써야 반영됩니다.
```
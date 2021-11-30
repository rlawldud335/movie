## 프로젝트 빌드 방법
> 빌드란?   
> 소스코드 파일을 컴퓨터에서 실행할 수 있는 독립 SW 가공물로 변환하는 과정, 결과물이다.
> 즉 작성한 소스코드(.java), 프로젝트에서 쓰인 각각의 파일 및 자원(.xml, .jpg, .jar, .properties ...)을 
> JVM이나 WAS가 인식할 수 있는 구조로 패키징 하는 과정 및 결과물이라고 할 수 있다.

- 사용한 빌드 툴 : maven

1. intelliJ로 maven 프로젝트 import 하기   
   https://kamang-it.tistory.com/entry/IntelliJmaven%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-intelliJ%EC%97%90-%EC%9E%84%ED%8F%B4%ED%8A%B8%ED%95%98%EA%B8%B0
2. Q 파일 생성   
   우측 Maven 탭 > movie > Lifecycle > compile 하면 Q파일 생성됨
3. maven 으로 빌드 후 실행


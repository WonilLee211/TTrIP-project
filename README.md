# ✈TTr!P [띱]


### 📃 목차

- [프로젝트 소개](#프로젝트-소개)
- [제가 기여한 주요 기능](#제가-기여한-주요-기능)
- [아키텍처](#아키텍처)
- [팀원 소개](#팀원-소개)
- [기술 스택 및 라이브러리](#기술-스택-및-라이브러리)

---

## ✏프로젝트 소개

<img src="https://github.com/serim53/Kkaddak/assets/67946662/fde04f31-038c-462e-9fa3-1155cc7e2070" width="180" height="320"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/355493a1-0d23-4c2e-872a-f083302ea165" width="180" height="320"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/204b1259-a7ba-42ff-8fe5-ed12f9a94fad" width="180" height="320"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/e1348b96-9837-4f4c-903a-4578a976a3bb" width="180" height="320"/><br/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/ed39a49f-162f-4925-8c0a-35ad702f540b" width="180" height="320"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/6948d507-3573-40c3-b830-1c3c2261fcf5" width="180" height="320"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/3f41dc25-53cb-4804-913a-f4dcd1238ade" width="180" height="320"/>


### 🛫TTr!P [여행 동행 매칭 서비스]


```markup
💚 여행을 준비하며 동행을 구해본 경험이 있으신가요?
더 쉽고, 편리한 동행 모집을 위해 TTr!P을 제작하게 되었습니다.
저희 서비스는 기존의 동행 모집 서비스와 다르게 여행 취향 테스트를 통한 여행 취향 매칭률을 제공하고, 
실시간 라이브 모드를 통한 현장 동행 모집 서비스를 제공합니다.
뱃지와 AR 방명록 등 재미요소도 추가하여 재미있게 사용할 수 있도록 구성했습니다. 
여행을 좋아하는 여행러들이 여행을 준비하는 과정, 여행 중, 여행 후까지 저희 앱을 꾸준히 활용할 수 있도록 기획했습니다.
```

* 개발 기간 : 23.04.10 ~ 23.05.19(6주)

---

## 🔍제가 기여한 주요 기능

- 제 역할은 백엔드 개발 담당자로서 **개발환경 구축, API 서버 개발, 컨텐트 추천 서버 개발, 리버스 프록시 서버 개발**을 수행했습니다.

### 유저간 매칭률 계산

<img src="https://github.com/serim53/Kkaddak/assets/67946662/cd16e64a-b380-4535-a59d-b7353e4ee7b8" width="180" height="380"/><img src="https://github.com/WonilLee211/TTrIP-project/assets/109330610/3ad74881-d0fa-4fb6-9c6a-ea1795704b15" width="180" height="380"/>
<p/>

* 회원정보와 취향테스트를 입력합니다. <br/>
* 취향 테스트의 결과는 다른 유저와의 매칭률(%)에 반영됩니다.<br/>
* 매칭률은 Euclidean Distance 유사도 계산법을 활용했습니다.<br/>

### 추천 게시글
<img src="https://github.com/serim53/Kkaddak/assets/67946662/71b41af4-4087-4789-af24-acf18da26f4c" width="180" height="380"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/b1668161-63c9-45e7-8c58-f5e21e4bc564" width="180" height="380"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/7b39d6f1-7c84-4884-b029-f00d28053a0b" width="180" height="380"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/f857f066-be33-4ddd-82d2-0862399fd1e2" width="180" height="380"/>
<p/>

* 게시글 등록 후 내용의 키워드를 분석하여 기존 게시글 중 비슷한 글을 추천하는 페이지입니다.<br/>
* TF-IDF 기반 Content base Filtering 알고리즘을 바탕으로 추천 시스템을 구현했습니다.<br/>

### 도시별 유저간 실시간 위치 공유 기능
<img src="https://github.com/serim53/Kkaddak/assets/67946662/2d606a87-968a-4b07-8da4-275699b53708" width="180" height="380"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/9187cfdd-4d4c-4861-809f-0c40101d44a3" width="180" height="380"/>
<img src="https://github.com/serim53/Kkaddak/assets/67946662/b940deac-1172-4632-bf10-633b5b173915" width="180" height="380"/>
<p/>

* 실시간으로 내 위치 주변의 앱 유저가 보여집니다.<br/>
* 유저 정보와 매칭률, 나와의 거리가 표시됩니다. 이때, 가까운 유저를 클릭하여 프로필을 확인하고 채팅을 걸 수 있습니다.<br/>


### 기타

- 리버스 프록시 서버 구축
- 개발 환경 구축
- 아키텍처 설계

---

## ⭐아키텍처

<img src="https://github.com/WonilLee211/TTrIP-project/assets/109330610/fc492e84-dbb5-485f-9172-2b66bb015bea" width="800"/>


서버 아키텍처는 크게 **얼굴 인식 서버(ai-server)와 게시글 추천 서버(recommend-server), 메인 api 서버(application)로 분리**됩니다. 데이터베이스는 MariaDB, Redis, MongoDB를 사용했습니다. Nginx는 들어오는 요청의 URL에 따라 해당 요청을 적절한 서버로 라우팅합니다.

이 아키텍처의 두 가지 주요 특징은 **ai-server의 Scale out과 게시글 추천 서버의 학습 서버와 조회 서버의 분리**입니다.

ai-server는 이미지 용량에 따라 최소 1~2초의 응답시간이 소요되기에 요청 트래픽 분산이 필수적이라 판단했습니다. 이에 Nginx에서 /face url 요청에 대해 **Least Connection 방식으로 로드 밸런싱**하여 트래픽을 분산시킵니다.

**게시글 학습 서버와 추천 게시글 조회 서버를 분리**했습니다. 학습 데이터는 **docker volume 공유**를 통해 데이터 무결성을 확보했습니다.

---

## 👨🏻‍🤝‍👨🏻팀원 소개

<img src="https://github.com/serim53/Kkaddak/assets/67946662/860cc96e-4aad-4595-a886-cc579b5d5fd0" width="800"/>

---


## 🔨기술 스택 및 라이브러리

## Backend

<h3>language</h3>

- ![Java](https://img.shields.io/badge/Java-11.0.15-007396.svg?style=for-the-badge&logo=java&logoColor=white) ![Python](https://img.shields.io/badge/Python-3.11.1-3776AB?style=for-the-badge&logo=python&logoColor=white)

<h3>framework</h3>

- ![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.7.9-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![Flask](https://img.shields.io/badge/Flask-1.1.4-000000?style=for-the-badge&logo=flask&logoColor=white)

<h3>library</h3>

- ![face_recognition](https://img.shields.io/badge/face_recognition-1.3.0-003545?style=for-the-badge&logo=face_recognition&logoColor=white) ![scikit-learn](https://img.shields.io/badge/scikit_learn-1.2.2-003545?style=for-the-badge&logo=scikit-learn&logoColor=white)
- ![Firebase](https://img.shields.io/badge/Firebase-9.1.1-FFCA28?style=for-the-badge&logo=firebase&logoColor=white) ![WebSocket](https://img.shields.io/badge/WebSocket-2.6.2-2F4F4F?style=for-the-badge&logo=WebSocket&logoColor=white)
- ![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-2.7.9-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Spring Batch](https://img.shields.io/badge/Spring_Batch-4.3.3-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Spring Security](https://img.shields.io/badge/Spring_Security-5.6.1-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![JUnit5](https://img.shields.io/badge/JUnit5-5.7.0-25A162?style=for-the-badge&logo=JUnit5&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-3.0.0-85EA2D.svg?style=for-the-badge&logo=swagger&logoColor=white)


<h3>database</h3>

- ![MariaDB](https://img.shields.io/badge/MariaDB-10.10.2-003545?style=for-the-badge&logo=MariaDB&logoColor=white) ![MongoDB](https://img.shields.io/badge/MongoDB-4.4.3-47A248?style=for-the-badge&logo=MongoDB&logoColor=white) ![Redis](https://img.shields.io/badge/Redis-7.0.8-DD0000?style=for-the-badge&logo=Redis&logoColor=white) ![H2 Database](https://img.shields.io/badge/H2_Database-2.7.9-E23E57?style=for-the-badge&logo=H2&logoColor=white)

<h3>infra</h3>

- ![Jenkins](https://img.shields.io/badge/Jenkins-lts-D24939?style=for-the-badge&logo=jenkins&logoColor=white)  ![Docker](https://img.shields.io/badge/Docker-4.15.0-2496ED.svg?style=for-the-badge&logo=docker&logoColor=white) ![Nginx](https://img.shields.io/badge/Nginx-1.21.1-269539.svg?style=for-the-badge&logo=nginx&logoColor=white)



<h3>build tools</h3>

- ![Gradle](https://img.shields.io/badge/Gradle-8.0-02303A?style=for-the-badge&logo=gradle&logoColor=white) 

---

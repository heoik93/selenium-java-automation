# 🛒 Laundry365 테스트 자동화 프로젝트

[![Selenium Automated Test](https://github.com/heoik93/selenium-java-automation/actions/workflows/github-action.yml/badge.svg)](https://github.com/heoik93/selenium-java-automation/actions/workflows/github-action.yml)
![Java](https://img.shields.io/badge/Java-21-blue)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)
![Jenkins](https://img.shields.io/badge/Jenkins-2.x-red)
![Docker](https://img.shields.io/badge/Docker-Latest-blue)

## 📋 1. 프로젝트 개요
* **목표:** 'Laundry365' 웹사이트의 핵심 기능 회귀 테스트 자동화
* **성과:** GitHub Actions를 통한 **테스트 자동화 및 리포팅** 시스템 구축

## 🛠 2. 기술 스택
| 분류 | 기술                         |
| :--- |:---------------------------|
| **Language** | Java 17                    |
| **Framework** | Selenium WebDriver, TestNG |
| **CI/CD** | GitHub Actions, Jenkins           |
| **Reporting** | ExtentReports              |

---

## 🚀 3. 트러블슈팅 (Troubleshooting)

### 3-1. 페이지 객체 모델(POM) 도입을 통한 유지보수성 향상
* **현상:** 서비스 UI 변경 시(버전 업데이트 등) 관련된 모든 테스트 코드를 일일이 수정해야 하는 번거로움 발생
* **원인:** 테스트 시나리오와 UI 요소 제어 로직이 결합되어 있어 작은 변화에도 전체 테스트 슈트가 깨지는 높은 의존성 문제
* **해결:** **POM 디자인 패턴**을 적용하여 UI 요소와 행위 로직을 별도 클래스로 분리, 유지보수 효율성 및 코드 재사용성 극대화

### 3-2. CI 서버 리소스 경합으로 인한 빌드 중단
* **현상:** GitHub Actions 서버에서 테스트 실행 중 로그가 멈추고 타임아웃 발생
* **원인:** 2-core CPU 환경에서 Maven 병렬 빌드와 TestNG 멀티 스레드가 충돌하여 **교착 상태(Deadlock)** 발생
* **해결:** 병렬 옵션을 제거하고 단일 스레드 순차 실행으로 전환하여 안정성 확보

### 3-3. 리눅스 Headless 모드 파일 업로드 및 제어 실패
* **현상:** 헤드리스 환경에서 `sendKeys`를 통한 이미지 업로드 기능이 무시되거나 세션이 응답하지 않음
* **원인:** OS 파일 탐색기 사용 불가 및 CDP 버전 불일치로 인한 브라우저-드라이버 간 통신 불안정
* **해결:** `JavascriptExecutor`로 요소를 강제 노출 후 경로 주입, `WebDriverWait`를 적용하여 성공률 100% 달성

### 3-4. 리포트 파일명 불일치 (404 에러)
* **현상:** GitHub Pages 배포 시 `index.html`을 찾지 못해 404 에러 발생
* **원인:** 이메일용 가변 파일명(Timestamp)과 웹 배포용 고정 파일명이 상충됨
* **해결:** 배포 직전 Shell Script를 통해 최신 리포트를 `index.html`로 복사하는 이원화 프로세스 구축

---

## 📊 4. 테스트 결과

### 4-1. 테스트 수행 요약
* **대상 서비스:** Laundry365 (Web)
* **주요 시나리오:**    
* **테스트 환경 (Cross-Platform)**
  - **Local:** Windows 10/11, Chrome (GUI 모드 - 디버깅 및 시나리오 검증용)
  - **CI/CD:** GitHub Actions Ubuntu-latest, Headless Chrome (자동화 빌드용)
* **수행 결과:** (현재테스트작성중)

### 4-2. 테스트 결과 리포트 (Dashboard)
테스트 결과 대시보드를 아래 링크에서 확인하실 수 있습니다.
* **[👉 실시간 테스트 리포트 확인하기] https://heoik93.github.io/selenium-java-automation/#**
* *(※ GitHub Actions 빌드 완료 시 자동으로 최신 결과가 반영됩니다.)*

---

## ✅ 5. 지속적 통합(CI) 및 테스트 결과 자동화

> **On-premise(Jenkins)**와 **Cloud(GitHub Actions)**를 결합한 하이브리드 CI 환경을 구축하여 운영 안정성을 확보했습니다.

### 🚀 5-1. Jenkins 기반 Local CI 환경 최적화
* **컨테이너 기반 테스트 환경 (`Docker`)**
    * 로컬 OS 및 종속성(Java/Maven) 충돌 방지를 위해 **Docker 컨테이너 기반 실행 환경** 구축
    * 환경 의존성을 완전히 제거하여 테스트의 **환경 독립성** 확보
* **지속적 통합 (`CI`) 프로세스 표준화**
    * 수동으로 환경을 세팅하던 비효율을 제거하고, 클릭 한 번으로 빌드-테스트-리포팅이 진행되는 **젠킨스 파이프라인** 정립
    * 코드 업데이트 시 즉각적인 품질 검증 및 190개 테스트 케이스의 안정적 완주 가능
* **알림 자동화 (`Notification`)**
    * 테스트 완료 즉시 상세 보고서를 **이메일(E-mail)로 자동 발송**하는 체계 구축

### ☁️ 5-2. Cloud CI (GitHub Actions) 연동 및 시각화
* **Headless 최적화**
    * 리눅스 환경에 최적화된 `Headless Chrome` 모드 설정 및 결함 없는 리포트 생성
    * 클라우드 환경에서도 로컬과 동일한 테스트 결과 도출
* **결과 호스팅 (`GitHub Pages`)**
    * 테스트 결과(Extent Report)를 **GitHub Pages에 자동 호스팅**
    * 별도 툴 설치 없이 웹 브라우저에서 **실시간 리포트 확인** 가능
---
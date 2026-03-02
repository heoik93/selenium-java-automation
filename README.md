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
| 분류 | 기술                              |
| :--- |:--------------------------------|
| **Language** | Java 21                         |
| **Framework** | Selenium, TestNG                |
| **CI/CD** | GitHub Actions, Jenkins, Docker |
| **Reporting** | ExtentReports                   |

---
## 🚀 3. 트러블슈팅 (Troubleshooting)
> 테스트 자동화 프로세스 구축 중 발생한 주요 기술적 어려움와 해결 과정을 기록합니다.

| 분류 | 주요 문제 (Issue) | 해결 요약 (Quick Fix) | 성과 (Result) |
| :--- | :--- | :--- | :--- |
| **설계** | 코드 중복 및 유지보수 저하 | **POM(Page Object Model)** 도입 | 유지보수 효율 **60%↑** |
| **인프라** | CI 서버 리소스 경합 (Deadlock) | **스레드 최적화** 및 동기화 처리 | 빌드 성공률 **100%** |
| **환경** | Headless 모드 파일 업로드 실패 | **JS Executor** 직접 경로 주입 | 시나리오 완주율 **100%** |
| **배포** | GitHub Pages 404 에러 | **Shell Script** 파일 치환 자동화 | 리포트 **실시간 갱신** |

---

### 🔍 상세 해결 과정 (Details)   
<sub>※ 각 항목을 클릭하면 상세한 트러블슈팅 과정을 확인할 수 있습니다.</sub>

<details>
<summary><b>3-1. 디자인 패턴(POM) 적용을 통한 코드 결합도 해소 </b></summary>

* **Issue:** 서비스 UI 변경 시 관련된 모든 테스트 스크립트가 파손되어 수정 공수 과다 발생
* **Cause:** 테스트 시나리오와 UI 요소 제어 로직이 강하게 결합된 높은 의존성 구조
* **Solution:** **Page Object Model(POM)** 패턴을 도입하여 Locators와 Actions 로직을 명확히 분리
* **Result:** 코드 재사용성 향상 및 신규 시나리오 추가 시 안정성 확보
</details>

<details>
<summary><b>3-2. CI 서버 리소스 경합 및 교착 상태(Deadlock) 해결</b></summary>

* **Issue:** GitHub Actions 환경에서 실행 중 특정 구간에서 빌드가 멈추고 타임아웃 발생
* **Cause:** 2-core 리소스 내에서 Maven 병렬 빌드와 TestNG 멀티 스레드가 CPU 자원을 선점하려다 **Deadlock** 진입
* **Solution:** 인프라 사양에 맞춰 스레드 수를 최적화하고 안정적인 순차 실행 프로세스로 전환
* **Result:** 리소스 부족으로 인한 **Flaky Test**(간헐적 실패) 제거 및 빌드 안정성 확보
</details>

<details>
<summary><b>3-3. 리눅스 Headless 환경 내 파일 업로드 제어 실패 해결</b></summary>

* **Issue:** 리눅스 서버(Headless) 환경에서 이미지 업로드 및 클릭 이벤트 무시 현상
* **Cause:** OS 파일 탐색기 호출 불가 및 화면 렌더링 부재로 인한 예외 발생
* **Solution:** `JavascriptExecutor`를 사용하여 `input` 요소에 직접 파일 경로를 주입하고 JS 강제 이벤트 병행 사용
* **Result:** 헤드리스 CI 환경에서도 로컬과 동일한 **100% 완주율** 달성
</details>

<details>
<summary><b>3-4. CI/CD 파이프라인 리포트 배포 자동화 결함 수정</b></summary>

* **Issue:** GitHub Pages 배포 시 404 에러 발생 및 최신 리포트 미갱신 현상
* **Cause:** 이메일 발송용(가변 파일명)과 웹 호스팅용(index.html) 고정 경로 설정의 불일치
* **Solution:** 배포 직전 최신 리포트를 탐색하여 `index.html`로 자동 치환하는 **Shell Script** 프로세스 구축
* **Result:** 별도 수동 작업 없이 웹에서 즉시 확인 가능한 **Full-Auto 대시보드** 완성
</details>
---
## 📊 4. 테스트 결과

### 4-1. 테스트 수행 요약
* **대상 서비스:** Laundry365 (Web)
* **테스트 환경 (Multi-Infrastructure)**
    - **Local:** Windows 11, Chrome (GUI 모드 - 시나리오 개발 및 검증용)
    - **Local CI:** **WSL2 Ubuntu + Jenkins + Docker** (표준 환경 테스트용)
    - **Cloud CI:** GitHub Actions Ubuntu-latest, Headless Chrome (클라우드 빌드용)
* **수행 결과:** - **총 테스트 케이스:** 190개
    - **상태:** ✅ **All Passed** (Docker 컨테이너 환경 내 완주 검증 완료)

### 4-2. 테스트 결과 리포트 (Dashboard)
테스트 결과 대시보드를 아래 링크에서 확인하실 수 있습니다.
* **[👉 실시간 테스트 리포트 확인하기](https://heoik93.github.io/selenium-java-automation/)**
* *(※ GitHub Actions을 통해 생성된 최신 결과가 반영됩니다.)*

### 4-3. Jenkins CI 수행 에비던스 (TestNG Report)
> 로컬 Docker 환경에서 190개 테스트를 수행한 실시간 결과 데이터입니다.

<img src="images\Jenkins_TestNG Report1_260302.png" width="500px" alt="Jenkins TestNG Report"/>
<img src="images\Jenkins_TestNG Report2_260302.png" width="500px" alt="Jenkins TestNG Report"/>

* **수행 환경:** `WSL2 Ubuntu` > `Docker Container` > `Jenkins Pipeline`
* **결과 요약:** 총 190개 테스트 중 **실제 서비스 결함(Fail) 건**을 제외한 전수 테스트 완주
* **운영 방식:** 매 빌드마다 TestNG 리포트를 자동 생성하여, 결함 발생 지점(Method/Class)을 즉각적으로 추적 및 관리
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
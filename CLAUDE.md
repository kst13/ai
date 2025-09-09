# CLAUDE.md

이 파일은 이 저장소에서 작업할 때 Claude Code (claude.ai/code)에게 지침을 제공합니다.

## 개발 명령어

**Java 버전 요구사항**: 이 프로젝트는 Java 17 이상이 필요합니다 (build.gradle.kts에서 Java 21로 설정됨). Java 11에서는 빌드가 실패합니다.

### 빌드 및 실행
```bash
# gradlew를 실행 가능하게 만들기 (필요한 경우)
chmod +x gradlew

# 프로젝트 빌드
./gradlew build

# 애플리케이션 실행 (Spring Boot)
./gradlew bootRun

# 테스트 실행
./gradlew test

# 빌드 결과물 정리
./gradlew clean
```

### API 엔드포인트 테스트
애플리케이션은 제공된 HTTP 파일을 사용하여 테스트할 수 있는 REST 엔드포인트를 노출합니다:
```bash
# http/example.http의 예제 HTTP 요청 사용
# 메인 엔드포인트: GET http://localhost:8080/ai/generate?message=your_message
```

## 아키텍처 개요

이것은 AI 채팅 기능을 위해 **Google Vertex AI Gemini**와 통합된 **Spring Boot 3.5.5** 애플리케이션입니다.

### 주요 구성요소

**메인 애플리케이션**: `AiApplication.java` - 표준 Spring Boot 진입점

**REST 컨트롤러**: `ChatController.java` (`src/main/java/kr/co/kst/ai/ui/`)
- 단일 엔드포인트: `GET /ai/generate`
- Vertex AI Gemini 채팅 모델과 통합
- `message` 매개변수 사용 (기본값: "Tell me a joke")
- AI가 생성한 응답과 함께 JSON 반환

**의존성**:
- Spring AI Vertex AI Gemini 통합 (`spring-ai-starter-model-vertex-ai-gemini`)
- Spring AI BOM 버전 1.0.1
- 테스트용 JUnit 5

### 설정

**Vertex AI 설정** (`src/main/resources/application.yml`):
- 프로젝트 ID: `ai-project-470709`
- 위치: `us-central1` 
- 모델: `gemini-2.0-flash`

애플리케이션은 Vertex AI 접근을 위해 적절한 Google Cloud 자격 증명이 구성되어 있어야 합니다.

### 프로젝트 구조
```
src/main/java/kr/co/kst/ai/
├── AiApplication.java          # 메인 Spring Boot 애플리케이션
└── ui/
    └── ChatController.java     # AI 채팅용 REST API 컨트롤러

src/main/resources/
└── application.yml             # Spring 및 Vertex AI 설정

http/
└── example.http               # 테스트용 HTTP 요청 예제
```

### 개발 참고사항

- 이 프로젝트는 Kotlin DSL을 사용하는 Gradle을 사용합니다
- AI 채팅 기능에 중점을 둔 단일 REST 엔드포인트 아키텍처
- 최소한의 테스트 커버리지 (컨텍스트 로딩 테스트만 존재)
- 수동 API 테스트를 위한 HTTP 클라이언트 예제 제공

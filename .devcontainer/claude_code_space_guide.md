# Claude Code Codespaces 환경

이 레포지토리는 GitHub Codespaces에서 Claude Code를 바로 사용할 수 있도록 미리 구성된 개발 환경입니다.

## 🚀 빠른 시작

### 1. Codespace 생성

1. 이 레포지토리에서 **Code** 버튼 클릭
1. **Codespaces** 탭 선택
1. **Create codespace on main** 클릭
1. 자동으로 Claude Code 환경이 설정됩니다 (2-3분 소요)

### 2. Claude Code 인증

Codespace가 준비되면 터미널에서:

```bash
# Claude Code 실행
claude

# 첫 실행 시 인증 방법 선택:
# Option 1: Anthropic Console (API 키)
# Option 2: Claude Pro/Max 구독
```

## 📋 포함된 환경

### 개발 도구

- ✅ **Node.js 20** (LTS)
- ✅ **Claude Code** (최신 버전)
- ✅ **TypeScript, ts-node, nodemon**
- ✅ **Git, GitHub CLI**
- ✅ **Docker-in-Docker**
- ✅ **ripgrep** (코드 검색 최적화)

### VS Code 확장 프로그램

- TypeScript 지원
- Prettier 코드 포매터
- JSON 지원
- Python 개발 환경
- Jupyter 노트북

### 포트 포워딩

- `3000`: 프론트엔드 개발 서버
- `8080`, `8081`: 백엔드 API
- `5000`: 기타 서비스

## 🎯 Claude Code 사용법

### 기본 명령어

```bash
# 프로젝트 폴더에서 Claude Code 시작
claude

# 도움말 보기
/help

# 현재 세션 비용 확인
/cost

# 터미널 설정 최적화
/terminal-setup
```

### 실제 사용 예시

#### 1. 코드 리팩토링

```
> 이 파일의 성능을 개선하고 가독성을 높여줘
```

#### 2. 버그 수정

```
> TypeError: Cannot read property 'name' of undefined 오류를 수정해줘
```

#### 3. 새 기능 개발

```
> 사용자 인증 기능을 추가해줘. JWT를 사용하고 Express.js 기반으로 만들어줘
```

#### 4. 코드 설명

```
> 이 프로젝트의 아키텍처를 설명해줘
```

#### 5. 테스트 작성

```
> 이 함수에 대한 Jest 테스트를 작성해줘
```

## ⚙️ 설정 파일

### `.devcontainer/devcontainer.json`

Codespaces 환경 설정을 정의합니다:

- 기본 이미지: Node.js 20
- 필요한 도구들 자동 설치
- VS Code 확장 프로그램 설정
- 포트 포워딩 설정

### `.devcontainer/setup-claude-code.sh`

Claude Code 설치 및 환경 설정 스크립트:

- npm 글로벌 설정 구성
- Claude Code 설치
- 추가 개발 도구 설치
- 환경 변수 설정

## 💰 비용 관리

### Anthropic API 사용 시

- **종량제**: 사용한 만큼 지불
- **예상 비용**: 중간 규모 프로젝트에서 월 $20-50
- **비용 확인**: `/cost` 명령어로 실시간 추적

### Claude Pro/Max 구독 시

- **월정액**: $20 (Pro) / $200 (Max)
- **무제한 사용**: 예측 가능한 비용
- **추천**: 정기적으로 사용한다면 더 경제적

## 🔧 고급 설정

### 커스텀 명령어 추가

`.claude/commands/` 폴더에 마크다운 파일을 생성하여 커스텀 명령어를 추가할 수 있습니다:

```markdown
<!-- .claude/commands/test.md -->
# 테스트 생성
$ARGUMENTS에 대한 Jest 테스트를 작성해주세요.
- React Testing Library 사용
- 모든 주요 기능 테스트
- 에지 케이스 포함
```

### GitHub PR 자동 리뷰

```bash
# GitHub 앱 설치
/install-github-app

# PR 자동 리뷰 설정
# claude-code-review.yml 파일이 생성됩니다
```

## 🛠️ 문제 해결

### Claude Code가 인식되지 않는 경우

```bash
# 터미널 재시작
source ~/.bashrc

# 또는 수동 설치
npm install -g @anthropic-ai/claude-code
```

### 권한 오류가 발생하는 경우

```bash
# 안전 모드 비활성화 (선택사항)
claude --dangerously-skip-permissions
```

### API 키 재설정

```bash
# 인증 정보 초기화
rm -rf ~/.claude
claude
```

## 📚 추가 자료

- [Claude Code 공식 문서](https://docs.anthropic.com/en/docs/claude-code/overview)
- [GitHub 레포지토리](https://github.com/anthropics/claude-code)
- [Anthropic API 가격](https://www.anthropic.com/pricing)

## 🤝 기여하기

이 환경 설정에 개선사항이 있다면 이슈나 PR을 생성해주세요!

-----

**Happy Coding with Claude! 🚀**
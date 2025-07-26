#!/bin/bash

echo “🚀 Claude Code Codespaces 환경 설정을 시작합니다…”

# Node.js 버전 확인

echo “📋 Node.js 버전 확인 중…”
node –version
npm –version

# npm 글로벌 패키지 경로 설정 (권한 문제 방지)

echo “🔧 npm 글로벌 설정 구성 중…”
mkdir -p ~/.npm-global
npm config set prefix ~/.npm-global

# PATH에 npm global bin 추가

echo “🛤️  PATH 환경 변수 설정 중…”
echo ‘export PATH=~/.npm-global/bin:$PATH’ >> ~/.bashrc
export PATH=~/.npm-global/bin:$PATH

# Claude Code 설치

echo “⚡ Claude Code 설치 중…”
npm install -g @anthropic-ai/claude-code

# 설치 확인

echo “✅ Claude Code 설치 완료 확인…”
if command -v claude &> /dev/null; then
echo “✅ Claude Code가 성공적으로 설치되었습니다!”
claude –version
else
echo “❌ Claude Code 설치에 실패했습니다.”
echo “🔍 문제 해결을 위해 다음을 시도해보세요:”
echo “   1. 터미널을 재시작한 후 ‘claude –version’ 실행”
echo “   2. 수동 설치: ‘npm install -g @anthropic-ai/claude-code’”
fi

# 유용한 개발 도구 설치

echo “🛠️  추가 개발 도구 설치 중…”
npm install -g typescript ts-node nodemon

# ripgrep 설치 (Claude Code의 코드베이스 검색 기능 향상)

echo “🔍 ripgrep 설치 중…”
sudo apt-get update && sudo apt-get install -y ripgrep

# Git 설정 안내

echo “📝 Git 설정 안내…”
echo “Git이 아직 설정되지 않았다면 다음 명령을 실행하세요:”
echo “git config –global user.name ‘Your Name’”
echo “git config –global user.email ‘your.email@example.com’”

# Claude Code 사용 안내

echo “”
echo “🎉 Claude Code 환경 설정이 완료되었습니다!”
echo “”
echo “📖 사용 방법:”
echo “1. 프로젝트 폴더에서 ‘claude’ 명령 실행”
echo “2. 첫 실행 시 Anthropic API 키 또는 Claude Pro/Max 계정으로 인증”
echo “3. 자연어로 코딩 작업 요청”
echo “”
echo “🔑 인증 옵션:”
echo “- Anthropic Console (API 키)”
echo “- Claude Pro/Max 구독”
echo “”
echo “💡 유용한 명령어:”
echo “- /help: 도움말 보기”
echo “- /cost: 현재 세션 비용 확인”
echo “- /bug: 버그 리포트”
echo “- /terminal-setup: 터미널 설정 최적화”
echo “”
echo “🌟 이제 ‘claude’ 명령으로 AI 페어 프로그래밍을 시작하세요!”

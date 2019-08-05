# 반응형 웹

> 1. 가변 그리드
> 2. 미디어 쿼리와 뷰포트
> 3. 플렉서블 박스



## 가변그리드

#### 가변 그리드 공식

- (가변 크기로 만들 박스의 가로 너비 / 가변 크기로 만들 박스를 감싸고 있는 박스의 가로 너비) * 100 = %

- `wrap` 또는 `wrapper`
  - `<div>`로 감싸면내용 전체의 크기나 배경색 등을 한꺼번에 조절할 수 있다.
  - 브라우저 화면 크기에 상관없이 웹 문서의 내용을 배치할 수 있다.
  - 가변 크기로 설정된 자식 박스들이 무제한으로 늘어나는 것을 방지하고 가변크기의 기준이 된다.



#### 가변 마진

- (가변 마진을 적용할 마진 값 / 적용할 박스를 감싸고 있는 박스의 가로 너비) * 100 = 가진 너비  % 값



#### 가변 패딩

- 일반적인 경우
  - (가변 패딩을 적용할 패딩 값 / 적용할 박스를 감싸고 있는 박스의 가로 너비) / 100 = 가변 패딩 % 값
- 제한적인 조건이 있을 떄 (박스의 정해진 너비 값 이상이 되지 말아야 하는 경우)
  - **자식 박스의 너비에서 양쪽 패딩 값을 빼고 공식을 적용한다.**
  - (가변 패딩을 적용할 패딩 값 / 적용할 박스를 감싸고 있는 박스의 가로 너비) / 100 = 가변 패딩 % 값



#### 고정 크기의 마진과 패딩을 위한  calc 함수

- calc(width 속성에서 사용할 수 있는 모든 값, 연산 기호)
- 가변 마진, 패딩을 적용할 박스의 너비에 calc(100% - `양쪽 마진 또는 패딩을 더한 값`)



#### 가변 폰트

- em
  - 대문자 M의 너비를 1em으로 표현한 것, 16px 정도
  - (가변 폰트를 적용할 글자 크기 값 / 적용할 요소를 감싸고 있는 요소의 글자 크기 값) = 가변 폰트 em 값
  - 가변 폰트 크기를 계산해주는 사이트 [PXtoEM](http://pxtoem.com)

- rem

  - em의 상속 문제를 해결해주는 단위
  - em은 부모 박스에 글자 크기가 지정 되어있을 경우 자식 박스에 상속한다.

- vw, vh, vmin, vmax

  - vw

    - 웹 브라우저의 너비를 100으로 잡고 크기를 결정하는 단위

    - (vw 단위를 적용할 글자 크기 값 * 브라우저의 너비 값) / 100 = 크기 값

      브라우저 너비가 1280일때 5vw는 (5 * 1280) / 100 = 64px이 된다.

  - vh

    - 웹 브라우저의 높이를 100으로 잡고 크기를 결정하는 단위
    - (vh 단위를 적용할 글자 크기 값 * 브라우저의 높이 값)/100 = 크기 값

  - vmin

    - 웹 브라우저의 너비와 높이 중 짧은 쪽을 기준으로 하여 크기를 결정하는 단위
    - 작은 쪽을 100으로 잡고 크기를 결정

  - vmax

    - 웹 브라우저의 너비와 높이 중 큰 쪽을 기준으로 하여 크기를 결정하는 단위
    - 큰 쪽을 100으로 잡고 크기를 결정



#### 가변 멀티미디어

- 가변 박스 안에서 `width`와 `max-width`를 100%로 설정한다.
  
- `max-width`를 100%로 설정하면 요소의 기본 크기 이상으로는 조절되지 않는다.
  
- 유투브, 비메오와 같은 플레이어를 제공하는 경우

  - 패딩 속성 이용하기

    ```html
    <head>
    ...
    <style>
    	...
    	#wrap {
    		position: relative;
    		padding-bottom: 56.25%;
    		/* 9 / 16 */
    		height: 0;
    		overflow: hidden;
    	}
    	
    	iframe {
    		position: absolute;
    		top: 0;
    		left: 0;
    		height: 100%;
    	}
    </style>
    <body>
    	<div id="wrap">
        	<iframe src="" frameborder="0"
                    webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe> 
        </div>    
    </body>
    ```

  - 스크립트 파일 사용하기



## 미디어 쿼리와 뷰포트

#### 미디어 쿼리

- 기본 문법

  ```
  @media [only 또는 not][미디어 유형][and 또는 ,콤마](조건문){실행문}
  ```

- @media: 미디어 쿼리의 시작을 알림

- "[only 또는 not]": only는 지원하는 브라우저에서만, not은 ~빼고 다

- 미디어 유형

  -  all: 모두
  - print: 인쇄장치
  - screen: 컴퓨터 화면 장치 또는 스마트 기기 화면
  - tv: 영상과 음성이 동시에 출력되는 장치
  - projection: 프로젝터 장치
  - handheld: 손에 들고 다니는 소형 장치
  - speech: 음성 출력 장치
  - aural: 음성 합성 장치(화면을 읽어 소리로 출력해 주는 장치)
  - embossed: 점자 인쇄 장치(화면을 읽어 종이에 점자를 찍어내는 장치)
  - tty: 디스플레이 기능이 제한된 장치
  - braille: 점자 표시 장치

- "[and 또는 ,콤마]"

  - and: 앞뒤 조건이 모두 사실일 때
  - , 콤마: 둘중 하나만 사실이라도
  - 생략가능

- 조건문

  ```css
  @media (min-width: 320px){실행문}
  @media (min-width: 320px) and (max-width: 720px){실행문}
  ```

  - width: 브라우저 너비 값
  - height: 브라우저 높이 값
  - device-width: 기기 너비 값
  - device-height: 기기 높이 값
  - orientation: 기기 화면 방향
  - aspect-ratio: 화면 비율
  - device-aspect-ratio: 기기의 화면 비율
  - color: 기기의 비트 수
  - color-index: 기기의 색상 수
  - monochrome: 기기가 흑백일 때 픽셀당 비트 수
  - resolution: 기기의 해상력
  - scan: TV의 스캔 방식
  - grid: 기기의 그리드/비트맵
  - min / max  : 이상/이하의 접두어

- 실행문

  - 일반적으로 사용하는 css코드

- 미디어 쿼리 적용 방식

  - 링크 방식

    - CSS 파일 내에 미디어 쿼리를 작성해서 `<link>`태그로 연결

      ```html
      <link rel="stylesheet" href="mediaqueries.css">
      ```

- 미디어 쿼리 주의사항
  - 띄어쓰기 주의
  - 접두사 사용시 작성 순서
    - min : 반드시 크기가 작은 순서대로
    - max: 반드시 크기가 큰 순서대로



#### 뷰포트

- 화면에서 실제 내용이 표시되는 영역

- 모바일의 경우 기본으로 설정되어 있는 뷰포트가 맞지 않아 미디어 쿼리가 작동하지 않을 수 있음

- 따라서, 메타 태그로 설정해줘야 한다.

  ```html
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
  ```

- 뷰포트 속성

  - width: device-width, 양수
  - height: device-height, 양수
  - initial-scale: 양수, 초기 배율
  - user-scalable: yes, no 뷰포트의 확대/축소 여부
  - minimum-scale: 양수 최소 축소 비율 기본값 0.25
  - maximum-scale: 양수 최대 확대 비율 기본값 5.0



## 플렉서블 박스

#### 개념

- 플렉서블 박스 = 부모 박스
- 플렉스 아이템 = 자식 박스
- 주축과 교차축 = 블렉서블 박스의 축



#### 플렉서블 박스 기술

- ```css
  display: flex or inline-flex // 플렉서블 박스로 만들기
  ```

- flex-direction 배치 방향

  - row
  - row-reverse
  - column
  - column-reverse

- flew-wrap 여러 줄로 배치하기

  - nowrap
  - wrap
  - wrap-reverse

- flex-flow 배치 방향과 여러 줄 배치하기

  - [flew-direction] [flex-wrap]

- justify-content 주축 방향 아이템 배치

  - flex-start
  - flex-end
  - center
  - space-between 양쪽 정렬
  - space-around 아이템간 자동 정렬

- align-items 교차축 방향으로 아이템 배치

  - stretch 
  - flex-start
  - flex-end
  - center
  - baseline 
    - 자식 박스들을 교차축의 시작점에 배치되는 자식 박스의 글자 베이스라인에 맞춰 배치

- align-self 아이템에 사용

  - auto
  - stretch
  - flex-start
  - flex-end
  - center
  - baseline

- auto 플렉서블 박스의 align-self 속성을 상속받음

- align-contentt 여러 줄일 떄 교차축 방향으로 아이템 배치

  - stretch
  - flex-start
  - flex-end
  - center
  - space-between
  - space-around

- order 플렉스 아이탬 배치 순서 바꾸기

  - 정수

- flex

  - [flex-grow]
    - 플렉서블 박스에 여백이 있을 때 플렉스 아이템의 크기를 늘릴 수 있는 속성, 속성값은 비율로 설정한다.
  - [flex-shrink]
    - 플렉서블 박스 안의 플렉스 아이템의 크기가 넘칠 경우 크기를 줄일 수 있는 속성, 속성값은 비율로 설정한다. 
  - [flex-basis]
    - 플렉스 아이템의 기본 크기를 설정하기 위한 속성, width 속성에서 사용할 수 있는 모든 값을 사용할 수 있다.
    - 
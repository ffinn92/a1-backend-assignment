# 에이원퍼포먼스팩토리 백엔드 사전과제
## 1. 과제 구현사항
- [x] 게시판은 리스트, 조회(읽기), 쓰기(수정) 화면으로 구성 됩니다.
- [x] 입력은 타이틀, 본문, 닉네임 입니다.
- [x] 한번 작성한 닉네임은 다음번 게시물 작성시 자동으로 입력 되도록 합니다.
- [x] 게시물에 대한 삭제가 가능하여 합니다.
- [x] 게시물에는 작성시 중요 게시물 체크가 가능합니다.
- [x] 리스트에서 중요 게시물의 경우에는 배경이 다른 색으로 표현이 되어야 합니다.
- [x] 타이틀과 닉네임에 대해 검색 기능을 추가 바랍니다.
- [ ] 데이터베이스를 사용하지 않고 구현 바랍니다.
- [ ] 페이징 기능을 추가 바랍니다.(옵션 사항)

## 2. 실행방법
- `backend/src/main/java/com/a1assignment/A1assignmentApplication.java`로 서버 실행
- `cd fronted`로 fronted 폴더에서 `/Java/a1-backend-assignment/frontend main >` `npm run dev`로 웹페이지 실행

## 3. API명세
기능|HTTP METHOD|URI|비고
:---:|:---:|:---|:---
게시글 조회|GET|`/api/posts/{id}`|
게시글 리스트 조회|GET|`/api/posts`|
게시글 키워드 조회|GET|`/api/posts/keywords?keyword={keyword}`|keyword란에 nickname 혹은 title로 조회가능
게시글 작성|POST|`/api/posts`|
게시글 수정|PUT|`/api/posts`|
게시글 삭제|DELETE|`/api/posts`|

## 4.테스트 사례
![image](https://user-images.githubusercontent.com/92678171/227759669-58fb61cb-5a2e-4b05-aea6-f0e853145521.png)

### ✏️ Rule
1. Issue, PR 시 Assignees를 할당 (누가 작업했는지 알 수 있게)
2. 작업 시작 전 Issue 생성 (Issue template 사용)
3. 브랜치명은 다음과 같이 사용
    - 새로운 기능 개발 : feat/#이슈번호, ex) `feat/#1`
    - 오류 수정 : fix/#이슈번호, ex) `fix/#1`
    - API 문서(swagger) 업데이트 : doc/#이슈번호, ex) `doc/#1`
    - 기타 : 상의 후 결정
4. 작업 완료 후 PR 생성
    - close, refs 등을 사용하여 Issue와 연결이 가능하다.
    - ex) close #1, refs #1
    - `close #1` 은 Issue 번호가 1번인 것과 연결되며 자동으로 Issue가 닫히게 된다.
    - `refs #1` 은 Issue 번호가 1번인 것과 연결되며 자동으로 Issue가 닫히지는 않는다.
5. main 브랜치에 merge 후 작업했던 branch는 삭제 (개발이 완료된 것으로 간주하기 위함)

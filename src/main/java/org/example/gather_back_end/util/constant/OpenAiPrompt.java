package org.example.gather_back_end.util.constant;

public class OpenAiPrompt {

    public static final String SYSTEM_PROMPT = """
        당신은 홍보 전략을 생성해주는 컨설턴트입니다. 최종 목적은 타임라인 구성을 위한 JSON 데이터를 만드는 것입니다. 아래와 같은 JSON 형식으로 홍보 전략을 생성해 달라고 요청이 올 것입니다.
        
        {
            "title": "OOO 동아리 모집",
            "period": 55,
            "targetNumberOfPeople": 392,
            "budget": 646000,
            "firstMeans": "PRINTS",
            "secondMeans": "VIDEO",
            "thirdMeans": "SNS_POSTS"
        }
        
        각 필드에 대한 설명을 해주겠습니다.
        
        title: 홍보 제목
        period: 예상 모집 기간
        targetNumberOfPeople: 목표 인원(이 값은 결과 데이터에 영향을 미치지 않습니다.)
        budget: 보유한 예산
        firstMeans: 원하는 홍보 수단1
        secondMeans: 원하는 홍보 수단2
        thirdMeans: 원하는 홍보 수단3
        
        (홍보 수단 종류는 다음 세 가지로 고정되어 있습니다. 인쇄물, 영상, SNS 게시물)
        
        PRINTS // 인쇄물 (포스터, 배너 등) 
        VIDEO // 영상 (숏폼, 미드폼, 롱폼 등) 
        SNS_POST // SNS 게시물 (카드뉴스, 피드 등) 
        
        홍보 수단은 무조건 1개 이상 작성되어 있습니다.
        
        그럼 아래와 같은 json 데이터 타입으로 응답을 해줘야 합니다.
        
        firstMeans에 대한 응답 오브젝트가 첫 번째, secondMeans에 대한 응답 오브젝트가 두 번째, thirdMeans에 대한 응답 오브젝트가 세 번째로 와야 합니다.
        
        - SNS_POST인 경우 tasks 배열에 들어가야 하는 name 필드는 "내용 정리 및 컨택", "제작", "게시_인스타그램" 입니다.
        - VIDEO인 경우 tasks 배열에 들어가야 하는 name 필드는 "예산 확정/기획", "제작사 선정", "협의", "촬영", "게시_틱톡", "게시_유튜브", "게시_인스타그램" 입니다.
        - PRINTS인 경우 tasks 배열에 들어가야 하는 name 필드는 "기획", "디자인", "출력", "게시요청_캠퍼스픽", "승인기간_캠퍼스픽", "최종게시_캠퍼스픽", "게시_요즘것들", "게시_인스타그램", "게시_에브리타임" 입니다. "게시요청_캠퍼스픽" 같은 경우 1일, "승인기간_캠퍼스픽" 같은 경우 2일이 소요됩니다.
        - tip 필드는 category와 tasks의 name에 따라 다른 문구가 들어가야 합니다. 상세문구는 너무 길어서 아래에 첨부하겠습니다.
        - tip 필드에 대한 상세 정보는 아래에 작성했습니다.
        
        
        - 실제 "tip" 필드에는 "상세문구"에 대한 내용이 들어가야 합니다.
        - 아래는 상세문구입니다.
        
        SNS_POST
        1. 내용 정리 및 컨택 (SNS_POST_TIP_1) : "단체의 키워드와 아이덴티티를 명확히 정리해, 디자이너와 최고의 결과물을 만들어보세요!"
        2. 제작 (SNS_POST_TIP_2) : "담당자를 지정해 크리에이터와 빠르고 예의 있게 소통하세요!"
        3. 게시 (SNS_POST_INSTAGRAM) : "일 5000원 투자로 짧은 기간에 최대 효과로 더 많은 사람들에게 정보를 알리세요!"
        
        PRINTS
        1. 기획 (PRINTS_TIP_1) : "단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!"
        2. 디자인 (PRINTS_TIP_2) : "단체의 색깔과 캐릭터를 정하고, 원하는 내용을 간결하게 정리해 디자이너와 원활히 소통하세요!"
        3. 출력 (PRINTS_TIP_3) : "시간적 여유가 있다면 Bizhows로, 급하다면 학교나 인쇄소에서 사이즈를 미리 확인 후 인쇄하세요!"
        4. 게시
            - 캠퍼스픽
                - 게시요청 단계 (PRINTS_CAMPUSPICK_REQUEST) : "접근성 높은 캠퍼스픽에 포스터를 준비해 효과적으로 홍보하세요!"
                - 승인기간 단계 (PRINTS_CAMPUSPICK_APPROVE_PERIOD) : "관리자의 승인을 받기까지 최대 48시간이 소요됩니다!"
                - 최종게시 단계 (PRINTS_CAMPUSPICK_FINAL_POST) : "캠퍼스픽에서 대학생들의 문의를 놓치지 않도록 매일 확인하세요!"
            - 요즘것들 (PRINTS_ALLFORYOUNG) : "대학생과 청년을 위한 다양한 활동 정보를 제공하는 요즘것들에 꼭 홍보하세요!"
            - 에브리타임 (PRINTS_EVERYTIME) : "대학생 대상 홍보엔 에브리타임이 최적! 3일 주기로 원하는 학과 게시판에 꾸준히 올려보세요!"
            - 인스타그램 (PRINTS_INSTAGRAM) : "20대를 타겟으로 인스타그램 유행을 활용해 효율적으로 홍보하세요!"
            
        VIDEO
        1. 예산 확정/기획 (VIDEO_TIP_1) : "단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!"
        2. 제작사 선정 (VIDEO_TIP_2) : "꼼꼼히 포트폴리오를 읽고, 다양한 옵션에서 본인과 맞는 디자이너를 찾아보세요!"
        3. 협의 (VIDEO_TIP_3) : "정확한 요구와 예의를 갖춰 소통하고, '투게더에서 보고 연락 드렸어요!'로 시작해보세요!"
        4. 촬영 (VIDEO_TIP_4) : "상호 배려와 사전 확정된 기획으로 원활한 촬영을 준비하세요!"
        5. 게시
            - 틱톡 (VIDEO_TIKTOK) : "트렌디한 틱톡으로 바이럴 주인공이 되어, 젊은 층을 사로잡아보세요!"
            - 유튜브 (VIDEO_YOUTUBE) : "700억 조회수의 유튜브 쇼츠로 짧고 강렬하게 단체를 홍보하세요!"
            - 인스타그램 (VIDEO_INSTAGRAM) : "일 5000원으로 릴스로 관심을 끌고, 바이럴의 주인공이 되어보세요!"
            
        
        [
        {
            "period": 55,
            "category": "PRINTS",
            "tasks": [
              { "name": "기획", "start": 0, "end": 2, "tip" : "PRINTS_TIP_1" },
              { "name": "디자인", "start": 2, "end": 7, "tip" : "PRINTS_TIP_2" },
              { "name": "출력", "start": 7, "end": 8, "tip" : "PRINTS_TIP_3" },
              { "name": "게시요청_캠퍼스픽", "start": 8, "end": 9, "tip" : "PRINTS_CAMPUSPICK_REQUEST" },
              { "name": "승인기간_캠퍼스픽", "start": 9, "end": 11, "tip" : "PRINTS_CAMPUSPICK_APPROVE_PERIOD" },
              { "name": "최종게시_캠퍼스픽", "start": 11, "end": 55, "tip" : "PRINTS_CAMPUSPICK_FINAL_POST" },
              { "name": "게시_요즘것들", "start": 8, "end": 55, "tip" : "PRINTS_ALLFORYOUNG" },
              { "name": "게시_에브리타임", "start": 8, "end": 55, "tip" : "PRINTS_EVERYTIME" },
              { "name": "게시_인스타그램", "start": 8, "end": 55, "tip" : "PRINTS_INSTAGRAM" }
            ]
          },
          {
            "period": 55,
            "category": "VIDEO",
            "tasks": [
              { "name": "예산 확정/기획", "start": 0, "end": 1, "tip" : "VIDEO_TIP_1" },
              { "name": "제작사 선정", "start": 1, "end": 3, "tip" : "VIDEO_TIP_2" },
              { "name": "협의", "start": 3, "end": 5, "tip" : "VIDEO_TIP_3" },
              { "name": "촬영", "start": 5, "end": 19, "tip" : "VIDEO_TIP_4" },
              { "name": "게시_틱톡", "start": 19, "end": 55, "tip" : "VIDEO_TIKTOK" },
              { "name": "게시_유튜브", "start": 19, "end": 55, "tip" : "VIDEO_YOUTUBE" },
              { "name": "게시_인스타그램", "start": 19, "end": 55, "tip" : "VIDEO_INSTAGRAM" }
            ]
          },
          {
            "period": 55,
            "category": "SNS_POST",
            "tasks": [
              { "name": "내용 정리 및 컨택", "start": 0, "end": 2, "tip" : "SNS_POST_TIP_1" },
              { "name": "제작", "start": 2, "end": 8, "tip" : "SNS_POST_TIP_2" },
              { "name": "게시_인스타그램", "start": 8, "end": 55, "tip" : "SNS_POST_INSTAGRAM" }
            ]
          }
        ]
        """;

    public static final String USER1 = """
            """;
    public static final String ASSISTANT1 = """
            """;
    // 더미데이터 22번
    public static final String USER2 = """
            {
            	"title": "4호선톤 서포터즈 구함",
            	"period": 76,
            	"targetNumberOfPeople": 154,
            	"budget": 167000,
            	"firstMeans": "VIDEO",
            	"secondMeans": "SNS_POST",
            	"thirdMEans": null
            }
            """;
    public static final String ASSISTANT2 = """
        [
           {
              "period":76,
              "category":"VIDEO",
              "tasks":[
                 {
                    "name":"예산 확정/기획",
                    "start":0,
                    "end":1,
                    "tip":"단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!"
                 },
                 {
                    "name":"제작사 선정",
                    "start":1,
                    "end":2,
                    "tip":"꼼꼼히 포트폴리오를 읽고, 다양한 옵션에서 본인과 맞는 디자이너를 찾아보세요!"
                 },
                 {
                    "name":"협의",
                    "start":2,
                    "end":4,
                    "tip":"정확한 요구와 예의를 갖춰 소통하고, '투게더에서 보고 연락 드렸어요!'로 시작해보세요!"
                 },
                 {
                    "name":"촬영",
                    "start":4,
                    "end":11,
                    "tip":"상호 배려와 사전 확정된 기획으로 원활한 촬영을 준비하세요!"
                 },
                 {
                    "name":"게시_틱톡",
                    "start":11,
                    "end":76,
                    "tip":"트렌디한 틱톡으로 바이럴 주인공이 되어, 젊은 층을 사로잡아보세요!"
                 },
                 {
                    "name":"게시_유튜브",
                    "start":11,
                    "end":76,
                    "tip":"700억 조회수의 유튜브 쇼츠로 짧고 강렬하게 단체를 홍보하세요!"
                 },
                 {
                    "name":"게시_인스타",
                    "start":11,
                    "end":76,
                    "tip":"일 5000원으로 릴스로 관심을 끌고, 바이럴의 주인공이 되어보세요!"
                 }
              ]
           },
           {
              "period":76,
              "category":"SNS_POST",
              "tasks":[
                 {
                    "name":"내용 정리 및 컨택",
                    "start":0,
                    "end":3,
                    "tip":"단체의 키워드와 아이덴티티를 명확히 정리해, 디자이너와 최고의 결과물을 만들어보세요!"
                 },
                 {
                    "name":"제작",
                    "start":3,
                    "end":10,
                    "tip":"담당자를 지정해 크리에이터와 빠르고 예의 있게 소통하세요!"
                 },
                 {
                    "name":"게시_인스타그램",
                    "start":10,
                    "end":76,
                    "tip":"일 5000원 투자로 짧은 기간에 최대 효과로 더 많은 사람들에게 정보를 알리세요!"
                 }
              ]
           }
        ]
            """;

    // 더미데이터 23번
    public static final String USER3 = """
            {
            	"title": "4호선톤 서포터즈 구함",
            	"period": 48,
            	"targetNumberOfPeople": 432,
            	"budget": 157000,
            	"firstMeans": "PRINTS",
            	"secondMeans": "SNS_POST",
            	"thirdMEans": null
            }
            """;
    public static final String ASSISTANT3 = """
        [
           {
              "period":48,
              "category":"PRINTS",
              "tasks":[
                 {
                    "name":"기획",
                    "start":0,
                    "end":2,
                    "tip":"단체의 매력을 정리해 소통을 준비하고, 크리에이터와 함께 아이디어를 실현하세요!"
                 },
                 {
                    "name":"디자인",
                    "start":2,
                    "end":10,
                    "tip":"단체의 색깔과 캐릭터를 정하고, 원하는 내용을 간결하게 정리해 디자이너와 원활히 소통하세요!"
                 },
                 {
                    "name":"출력",
                    "start":10,
                    "end":11,
                    "tip":"시간적 여유가 있다면 Bizhows로, 급하다면 학교나 인쇄소에서 사이즈를 미리 확인 후 인쇄하세요!"
                 },
                 {
                    "name":"게시요청_캠퍼스픽",
                    "start":11,
                    "end":12,
                    "tip":"접근성 높은 캠퍼스픽에 포스터를 준비해 효과적으로 홍보하세요!"
                 },
                 {
                    "name":"승인기간_캠퍼스픽",
                    "start":12,
                    "end":14,
                    "tip":"관리자의 승인을 받기까지 최대 48시간이 소요됩니다!"
                 },
                 {
                    "name":"최종게시_캠퍼스픽",
                    "start":14,
                    "end":48,
                    "tip":"캠퍼스픽에서 대학생들의 문의를 놓치지 않도록 매일 확인하세요!"
                 },
                 {
                    "name":"게시_요즘것들",
                    "start":11,
                    "end":48,
                    "tip":"대학생과 청년을 위한 다양한 활동 정보를 제공하는 요즘것들에 꼭 홍보하세요!"
                 },
                 {
                    "name":"게시_에브리타임",
                    "start":11,
                    "end":48,
                    "tip":"대학생 대상 홍보엔 에브리타임이 최적! 3일 주기로 원하는 학과 게시판에 꾸준히 올려보세요!"
                 },
                 {
                    "name":"게시_인스타그램",
                    "start":11,
                    "end":48,
                    "tip":"20대를 타겟으로 인스타그램 유행을 활용해 효율적으로 홍보하세요!"
                 }
              ]
           },
           {
              "period":48,
              "category":"SNS_POST",
              "tasks":[
                 {
                    "name":"내용 정리 및 컨택",
                    "start":0,
                    "end":2,
                    "tip":"단체의 키워드와 아이덴티티를 명확히 정리해, 디자이너와 최고의 결과물을 만들어보세요!"
                 },
                 {
                    "name":"제작",
                    "start":2,
                    "end":9,
                    "tip":"담당자를 지정해 크리에이터와 빠르고 예의 있게 소통하세요!"
                 },
                 {
                    "name":"게시_인스타그램",
                    "start":9,
                    "end":39,
                    "tip":"일 5000원 투자로 짧은 기간에 최대 효과로 더 많은 사람들에게 정보를 알리세요!"
                 }
              ]
           }
        ]
            """;
    public static final String FINAL_REQUEST_PROMPT = "json 형태로 결과 데이터만 주세요.";
}

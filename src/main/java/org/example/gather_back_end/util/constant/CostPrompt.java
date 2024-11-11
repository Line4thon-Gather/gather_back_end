package org.example.gather_back_end.util.constant;

/**
 * 홍보 비용 관련 프롬프트
 */
public class CostPrompt {

    public static final String SYSTEM_PROMPT = """
                당신은 홍보 전략을 생성해주는 컨설턴트입니다. 최종 목적은 홍보 비용 관리를 위한 JSON 데이터를 만드는 것입니다. 아래와 같은 JSON 형식으로 홍보 전략을 생성해 달라고 요청이 올 것입니다.
                        
                {
                    "budget": 646000,
                    "firstMeans": "PRINTS",
                    "secondMeans": "VIDEO",
                    "thirdMeans": "SNS_POST",
                    "instagramPromotionPeriod" : 20
                }
                        
                각 필드에 대한 설명을 해주겠습니다.
                        
                budget: 보유한 총 예산
                firstMeans: 원하는 홍보 수단1
                secondMeans: 원하는 홍보 수단2
                thirdMeans: 원하는 홍보 수단3
                instagramPromotionPeriod: 인스타그램 홍보 기간
                        
                (홍보 수단 종류는 다음 세 가지로 고정되어 있습니다. 인쇄물, 영상, SNS 게시물)
                        
                PRINTS // 인쇄물 (포스터, 배너 등)
                VIDEO // 영상 (숏폼, 미드폼, 롱폼 등)
                SNS_POST // SNS 게시물 (카드뉴스, 피드 등)
                        
                홍보 수단은 무조건 1개 이상 작성되어 있습니다.
                        
                그럼 아래와 같은 json 데이터 타입으로 응답을 해줘야 합니다.
                        
                firstMeans에 대한 응답 오브젝트가 첫 번째, secondMeans에 대한 응답 오브젝트가 두 번째, thirdMeans에 대한 응답 오브젝트가 세 번째로 와야 합니다.
                        
                - SNS_POST인 경우 means 필드는 "SNS 게시물" 이어야 합니다.
                - VIDEO인 경우 means 필드는 "영상" 이어야 합니다.
                - PRINTS인 경우 means 필드는 "인쇄물" 이어야 합니다.
                        
                - 핵심적으로 봐줘야 할 것은 cost와 rate를 구하는 부분입니다.
                 \s
                cost 구하는 방법
                1. 사용 가능한 비용 = (Request로 받은 budget 필드) - (5000 * instagramPromotionPeriod) 입니다. 예시를 들면, 현재 Request에서 budget이 646000이므로 사용 가능한 금액 = (646000) - (5000 * 10) = 546000 이 됩니다.
                2-1. firstMeans에만 값이 있는 경우, 그 값을 100% 사용하도록 측정, 즉, cost 필드는 1.에서 구한 "사용 가능한 비용" 그대로 사용합니다. 예시를 들면, 첫 번째 오브젝트의 cost는 "646,000"이 됩니다.
                2-2. firstMeans와 secondMeans에만 값이 있는 경우, firstMeans는 1번에서 구한 "사용 가능한 비용"의 70%, secondMeans는 "사용 가능한 비용"의 30%를 사용하도록 측정합니다. 예시를 들면, 첫 번째 오브젝트의 cost는 "452,200", 두 번째 오브젝트의 cost는 "193,800" 입니다.
                2-3. firstMeans, secondMeans, thirdMeans 모두에 값이 있는 경우 firstMeans는 1번에서 구한 "사용 가능한 비용"의 70%, secondMeans는 "사용 가능한 비용"의 20%, thirdMeans는 "사용 가능한 비용"의 10%를 사용하도록 측정합니다. 예시를 들면, 첫 번째 오브젝트의 cost는 "452,200", 두 번째 오브젝트의 cost는 "129,200", 세 번째 오브젝트의 cost는 "64,600" 입니다.\s
                        
                rate 구하는 방법
                1. 각 오브젝트의 cost를 모두 더합니다.
                2. 100분율로 계산했을 때의 % 수치를 넣습니다.
                        
                응답 형식에서 주의사항은 두 가지 입니다.
                1. cost에서 천 원 단위로 쉼표를 붙여주세요.
                2. rate에서 %는 제외하고 숫자만 들어가게 해주세요.
                	
                [
                  {
                      "means": "인쇄물",
                      "cost": "452,200",
                      "rate": 70
                  },
                  {
                      "means": "영상",
                      "cost": "129,200",
                      "rate": 20
                  },
                  {
                      "means": "SNS 게시물",
                      "cost": "64,600",
                      "rate": 10
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
                "budget": 167000,
                "firstMeans": "VIDEO",
                "secondMeans": "SNS_POST",
                "thirdMeans": null,
                "instagramPromotionPeriod" : 7
            }
            """;
    public static final String ASSISTANT2 = """
            [
              {
                  "means": "영상",
                  "cost": "92,400",
                  "rate": 70
              },
              {
                  "means": "SNS 게시물",
                  "cost": "39,600",
                  "rate": 30
              }
            ]
            """;

    // 더미데이터 23번
    public static final String USER3 = """
            {
                "budget": 157000,
                "firstMeans": "PRINTS",
                "secondMeans": "SNS_POST",
                "thirdMeans": null,
                "instagramPromotionPeriod" : 10
            }
            """;
    public static final String ASSISTANT3 = """
            [
              {
                  "means": "인쇄물",
                  "cost": "74,900",
                  "rate": 70
              },
              {
                  "means": "SNS 게시물",
                  "cost": "32,100",
                  "rate": 30
              }
            ]
            """;
    public static final String FINAL_REQUEST_PROMPT = "json 형태로 결과 데이터만 주세요.";
}

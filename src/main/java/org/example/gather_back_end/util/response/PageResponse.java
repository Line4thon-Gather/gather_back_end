package org.example.gather_back_end.util.response;

/**
 * 게시글 등의 리스트 조회 시 필요한 응답 클래스
 * Pageable 이용
 */
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@Builder
public class PageResponse<T> {

    @Schema(description = "총 페이지 수", example = "1")
    private int totalPage;

    @Schema(description = "결과 데이터 개수", example = "3")
    private Long totalElements;

    @Schema(description = "한 번에 가져올 데이터 개수", example = "12")
    private int pagingSize;

    @Schema(description = "현재 페이지 (1부터 시작)", example = "1")
    private int currentPage;

    @Schema(description = "첫 번째 페이지인지 여부", example = "true")
    private Boolean isFirst;

    @Schema(description = "마지막 페이지인지 여부", example = "false")
    private Boolean isLast;

    @Schema(description = "결과 데이터가 비어있는지 여부", example = "false")
    private Boolean isEmpty;

    @Schema(description = "실제 데이터가 담기는 필드")
    private List<T> data;

    public static PageResponse of(Page page) {
        return PageResponse.builder()
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .pagingSize(page.getSize())
                .currentPage(page.getNumber() + 1)
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .isEmpty(page.isEmpty())
                .data(page.getContent())
                .build();
    }
}

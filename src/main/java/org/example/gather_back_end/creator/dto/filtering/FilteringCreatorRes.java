package org.example.gather_back_end.creator.dto.filtering;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.example.gather_back_end.util.response.PageResponse;

public record FilteringCreatorRes(
        PageResponse<?> pageInfo,
        List<CreatorInfo> creatorInfo
) {
}

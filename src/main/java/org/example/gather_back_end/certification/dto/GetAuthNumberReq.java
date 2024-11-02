package org.example.gather_back_end.certification.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class GetAuthNumberReq {
    private String key;
    private String email;
    private String univName;
    private boolean univCheck;
}

package org.example.gather_back_end.certification.dto;

import java.util.List;

public record GetEmailExistCheckRes(
        String username,
        String domain,
        String emailAddress,
        boolean formatCheck,
        boolean smtpCheck,
        boolean dnsCheck,
        boolean freeCheck,
        boolean disposableCheck,
        boolean catchAllCheck,
        List<String> mxRecords,
        Audit audit
) {
    public record Audit(
            String auditCreatedDate,
            String auditUpdatedDate
    ) {}
}

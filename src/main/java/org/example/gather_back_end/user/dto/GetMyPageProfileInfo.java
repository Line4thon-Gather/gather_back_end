package org.example.gather_back_end.user.dto;

public record GetMyPageProfileInfo(
        String profileImgUrl, // 프로필 이미지
        String role, // 크리에이터인지 아닌지
        String email
) {

    // 정적 팩토리 메서드
    public static GetMyPageProfileInfo of(String profileImgUrl, String role, String email) {
        return new GetMyPageProfileInfo(profileImgUrl, role, email);
    }

}

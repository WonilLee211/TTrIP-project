package com.ttrip.api.dto.memberDto.memberResDto;

import com.ttrip.api.dto.tokenDto.TokenDto;
import com.ttrip.core.entity.member.Member;
import com.ttrip.core.customEnum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResDto {
    //private Member member;
    private String phoneNumber;
    private String nickname;
    private String intro;
    private String imagePath;
    private String fcmToken;
    private Gender gender;
    private LocalDate birthday;
    private Boolean shareLocation;
    private TokenDto tokenDto;

    public static MemberResDto toBuild(Member member, TokenDto tokenDto)
    {
        return MemberResDto.builder()
                .phoneNumber(member.getPhoneNumber())
                .nickname(member.getNickname())
                .intro(member.getIntro())
                .imagePath(member.getImagePath())
                .fcmToken(member.getFcmToken())
                .gender(member.getGender())
                .birthday(member.getBirthday())
                .shareLocation(member.getShareLocation())
                .tokenDto(tokenDto)
                .build();
    }
}
package com.nalaolla.www.dto;

import com.nalaolla.www.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long seq;
    private String userid;
    private String name;
    private String email;
    private String password;
    private LocalDateTime regdate;
    private LocalDateTime moddate;

    public MemberEntity toEntity() {
        MemberEntity memberEntity = MemberEntity.builder()
                .seq(seq)
                .userid(userid)
                .name(name)
                .email(email)
                .password(password)
                .build();

        return memberEntity;
    }

    @Builder
    public MemberDto(Long seq, String userid, String name, String email, String password, LocalDateTime regdate, LocalDateTime moddate) {
        this.seq = seq;
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.regdate = regdate;
        this.moddate = moddate;
    }

}

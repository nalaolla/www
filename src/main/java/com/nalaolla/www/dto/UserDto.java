package com.nalaolla.www.dto;

import com.nalaolla.www.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
    private Long seq;
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime regdate;
    private LocalDateTime moddate;

    public MemberEntity toEntity() {
        MemberEntity memberEntity = MemberEntity.builder()
                .seq(seq)
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .build();

        return memberEntity;
    }

    @Builder
    public UserDto(Long seq, String id, String name, String email, String password, LocalDateTime regdate, LocalDateTime moddate) {
        this.seq = seq;
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.regdate = regdate;
        this.moddate = moddate;
    }

}

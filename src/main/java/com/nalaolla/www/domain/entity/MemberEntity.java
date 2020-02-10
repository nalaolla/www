package com.nalaolla.www.domain.entity;

import com.nalaolla.www.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 100, nullable = false)
    private String id;

    @Column(length = 200, nullable = true)
    private String email;

    @Column(length = 30, nullable = true)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public MemberEntity(Long seq, String id, String email, String name, String password) {
        this.seq = seq;
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

package com.nalaolla.www.domain.entity;

import com.nalaolla.www.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class MemberEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 100, nullable = false)
    private String userid;

    @Column(length = 200, nullable = true)
    private String email;

    @Column(length = 30, nullable = true)
    private String name;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public MemberEntity(Long seq, String userid, String email, String name, String password) {
        this.seq = seq;
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

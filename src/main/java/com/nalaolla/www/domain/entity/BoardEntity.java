package com.nalaolla.www.domain.entity;

import com.nalaolla.www.config.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board") //정의하지 않으면 클래스명으로 생성됨
public class BoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 30, nullable = false)
    private String userid;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String contents;

    @Builder
    public BoardEntity(Long seq, String title, String contents, String userid) {
        this.seq = seq;
        this.title = title;
        this.contents = contents;
        this.userid = userid;
    }
}

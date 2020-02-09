package com.nalaolla.www.dto;

import com.nalaolla.www.domain.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long seq;
    private String title;
    private String contents;
    private String userid;
    private LocalDateTime regdate;
    private LocalDateTime moddate;

    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .seq(seq)
                .title(title)
                .contents(contents)
                .userid(userid)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long seq, String title, String contents, String userid, LocalDateTime regdate, LocalDateTime moddate) {
        this.seq = seq;
        this.title = title;
        this.contents = contents;
        this.userid = userid;
        this.regdate = regdate;
        this.moddate = moddate;
    }

}

package com.nalaolla.www.config;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDate)도 컬럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class)  //BaseTimeEntity클래스에 Auditing 기능을 포함시킵니다.
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime regdate;

    @LastModifiedDate
    private LocalDateTime moddate;
}

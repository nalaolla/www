package com.nalaolla.www.domain.repository;

import com.nalaolla.www.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //id를 Where 조건절로 하여, 데이터를 가져올 수 있도록 findbyUserid() 메서드를 정의.
    Optional<MemberEntity> findByUserid(String userid);

    MemberEntity findBySeq(Long seq);


}

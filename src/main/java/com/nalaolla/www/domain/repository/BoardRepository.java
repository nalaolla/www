package com.nalaolla.www.domain.repository;

import com.nalaolla.www.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    /**
     * findByTitleContaining()
     * 검색을 직접적으로 호출하는 메서드.
     * JpaRepository에서 메서드명의 By 이후는 SQL의 where 조건 절에 대응되는 것인데, 이렇게 Containing을 붙여주면 Like 검색이 됩니다.
     * 즉, 해당 함수는 %{keyword}% 이렇게 표현이 됩니다. (findby + column + Containing())
     *
     * JPA like query를 사용하는 다양한 방법.
     * StartsWith : 검색어로 시작하는 Like 검색, {keyword}%
     * EndsWith : 검색어로 끝는 Like 검색, %{keyword}
     * IgnoreCase : 대소문자 구분 없이 검색
     * Not : 검색어를 포함하지 않는 검색
     */
}

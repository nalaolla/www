package com.nalaolla.www.restApi.controller;

import com.nalaolla.www.domain.entity.MemberEntity;
import com.nalaolla.www.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"2. user"})
@AllArgsConstructor
@RestController
@RequestMapping("/userApi")
public class MemberRestController {

    MemberService memberService;

    @ApiOperation(value = "회원정보 조회", notes = "회원정보 조회.")
    @GetMapping("/getMemberInfo")
    public ResponseEntity<Map<String, Object>> getMemberInfo(Long seq) {
        MemberEntity memberDto = memberService.loadUserBySeq(seq);

        Map<String, Object> result = new HashMap<>();
        result.put("memberInfo", memberDto);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

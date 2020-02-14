package com.nalaolla.www.restApi.controller;

import com.nalaolla.www.dto.BoardDto;
import com.nalaolla.www.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = {"1. Board"})
@AllArgsConstructor
@RestController
@RequestMapping("/boardRest")
public class BoardRestController {

    private BoardService boardService;

    @ApiOperation(value = "게시글 등록", notes = "대충 등록한다.")
    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> write(@RequestBody BoardDto dto) {
        Long result = boardService.saveBoard(dto);

        Map<String, Object> resultMessage = new HashMap<>();
        resultMessage.put("message", result);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping("/update")
    public String update(@RequestBody BoardDto dto) {
        boardService.updateBoard(dto);
        return "/board/detail/"+dto.getSeq();
    }
}

package com.nalaolla.www.restController;

import com.nalaolla.www.dto.BoardDto;
import com.nalaolla.www.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardRestController {

    private BoardService boardService;

    @PostMapping("/write")
    public String write(@RequestBody BoardDto dto) {
        Long result = boardService.saveBoard(dto);
        return result.toString();
    }

    @PostMapping("/update")
    public String update(@RequestBody BoardDto dto) {
        return null;
    }
}

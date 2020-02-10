package com.nalaolla.www.controller;

import com.nalaolla.www.dto.BoardDto;
import com.nalaolla.www.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    @GetMapping("/list")
    public String getList(Model model) {
        List<BoardDto> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "/board/list";
    }

    @GetMapping("/detail")
    public ModelAndView getDetail(Long seq) {
        ModelAndView mv = new ModelAndView();

        BoardDto dto = boardService.getDetail(seq);

        mv.setViewName("/board/detail");
        mv.addObject("detail", dto);

        return mv;
    }


}

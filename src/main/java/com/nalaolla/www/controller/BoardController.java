package com.nalaolla.www.controller;

import com.nalaolla.www.dto.BoardDto;
import com.nalaolla.www.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("listPage")
    public String getListPage(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        List<BoardDto> boardList = boardService.getBoardPageList(page);

        Long pageCount = boardService.getPageCount();

        List<Integer> pageList = boardService.getPageList(page);

        return "/board/listPage";
    }

    @GetMapping("/detail/{seq}")
    public ModelAndView getDetail(Long seq) {
        ModelAndView mv = new ModelAndView();

        BoardDto dto = boardService.getDetail(seq);

        mv.setViewName("/board/detail");
        mv.addObject("detail", dto);

        return mv;
    }

    @GetMapping("/delete/{seq}")
    public String delete(@PathVariable("seq") Long seq) {
        boardService.delete(seq);
        return "redirect:/board/list";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        List<BoardDto> boardList = boardService.search(keyword);
        model.addAttribute("boardList", boardList);
        return "/board/list";
    }


}

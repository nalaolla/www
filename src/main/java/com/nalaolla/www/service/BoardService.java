package com.nalaolla.www.service;

import com.nalaolla.www.domain.entity.BoardEntity;
import com.nalaolla.www.domain.repository.BoardRepository;
import com.nalaolla.www.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Transactional
    public Long saveBoard(BoardDto dto) {
        return boardRepository.save(dto.toEntity()).getSeq();
    }

    @Transactional
    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

        for(BoardEntity blist : boardList){
            BoardDto boardDto = BoardDto.builder()
                    .seq(blist.getSeq())
                    .title(blist.getTitle())
                    .userid(blist.getUserid())
                    .contents(blist.getContents())
                    .regdate(blist.getRegdate())
                    .moddate(blist.getModdate())
                    .build();
            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }
}

package com.nalaolla.www.service;

import com.nalaolla.www.domain.entity.BoardEntity;
import com.nalaolla.www.domain.repository.BoardRepository;
import com.nalaolla.www.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class BoardService {

    private static final int PAGE_SIZE = 3;
    private static final int PAGING_COUNT = 5;

    private BoardRepository boardRepository;

    public Long saveBoard(BoardDto dto) {
        return boardRepository.save(dto.toEntity()).getSeq();
    }

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

    public List<BoardDto> getBoardPageList(Integer pageNum) {
        Page<BoardEntity> page = boardRepository.findAll(PageRequest.of(pageNum-1, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "seq")));
        List<BoardEntity> boardEntities = page.getContent();
        System.out.println(page.getTotalPages());
        //Page class를 활용하면 페이징 처리를 쉽게 할 수 있을듯 싶다..
        return null;
    }

    public Long getPageCount() {
        return boardRepository.count();
    }

    public List<Integer> getPageList(Integer pageNum) {

        Long totalPage = getPageCount();

        List<Integer> pageList = new ArrayList<>();
        return pageList;
    }

    public BoardDto getDetail(Long seq) {
        Optional<BoardEntity> dto = boardRepository.findById(seq);

        BoardEntity boardDetail = dto.get();

        BoardDto boardDto = BoardDto.builder()
                .seq(boardDetail.getSeq())
                .title(boardDetail.getTitle())
                .userid(boardDetail.getUserid())
                .contents(boardDetail.getContents())
                .regdate(boardDetail.getRegdate())
                .moddate(boardDetail.getModdate())
                .build();

        return boardDto;
    }

    /**
     *
     * @param boardDto
     * @return
     */
    public Long updateBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getSeq();
    }

    public void delete(Long seq) {
        boardRepository.deleteById(seq);
    }

    public List<BoardDto> search(String keyword) {
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> list = new ArrayList<BoardDto>();

        if (boardEntities.isEmpty()) {
            return list;
        }

        for(BoardEntity boardEntity : boardEntities) {
            list.add(convertEntityToDto(boardEntity));
        }
        return list;
    }

    private BoardDto convertEntityToDto(BoardEntity boardEntity) {
        BoardDto dto = BoardDto.builder()
                .seq(boardEntity.getSeq())
                .title(boardEntity.getTitle())
                .userid(boardEntity.getUserid())
                .contents(boardEntity.getContents())
                .regdate(boardEntity.getRegdate())
                .moddate(boardEntity.getModdate())
                .build();

        return dto;
    }
}

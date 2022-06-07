package jejunu.portal.calenboard.service;

import jejunu.portal.calenboard.dto.BoardDto;
import jejunu.portal.calenboard.entity.Board;
import jejunu.portal.calenboard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@AllArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public Long create(BoardDto requestDto){
        return boardRepository.save(requestDto.toEntity()).getBid();
    }

    public Long update(BoardDto requestDto){
        Long bid = requestDto.getBid();
        Board board = boardRepository.findById(bid)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return boardRepository.save(requestDto.toEntity()).getBid();
    }

    public Optional<Board> findByDate(LocalDate date){
        Long bid = boardRepository.findByDate(date).getBid();
        return boardRepository.findById(bid);
    }

}

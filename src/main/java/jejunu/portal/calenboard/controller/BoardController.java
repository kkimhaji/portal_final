package jejunu.portal.calenboard.controller;

import jejunu.portal.calenboard.dto.BoardDTO;
import jejunu.portal.calenboard.entity.Board;
import jejunu.portal.calenboard.repository.BoardRepository;
import jejunu.portal.calenboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("/post")
    public Long create(@ModelAttribute BoardDTO boardDTO, HttpServletRequest request, @RequestParam("uploadFiles") MultipartFile[] uploadFiles) throws Exception {
        return boardService.create(boardDTO, request, uploadFiles);
    }

    @PutMapping("/update")
    public Long updateBoard(@RequestBody BoardDTO boardDto, HttpServletRequest request){
        return boardService.update(boardDto, request);
    }

    @GetMapping("getList")
    public List<Board> getlist(HttpServletRequest request){
        return boardService.getlistAll(request);
    }

    @GetMapping("get/{bid}")
    public Optional<Board> get(@PathVariable Long bid){
        return boardService.get(bid);
    }


    @DeleteMapping("/delete/{bid}")
    public Long delete(@PathVariable Long bid){
        boardRepository.deleteById(bid);
        return bid;
    }

    @GetMapping("/getbydate")
    public Optional<Board> getByDate(@RequestParam String nowDate, HttpServletRequest request){
        return boardService.findByDate(nowDate, request);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam MultipartFile[] uploadFiles){
        for (MultipartFile uploadFile: uploadFiles){
            String originName = uploadFile.getOriginalFilename();
            String fileName = originName.substring(originName.lastIndexOf("\\"+1));
            System.out.println("fileName: "+fileName);
        }
    }

    @GetMapping("/postexist")
    public boolean ifexist(@RequestParam String nowDate, HttpServletRequest request){
        return boardService.postexist(nowDate, request);
    }

}

package com.likelion.session.controller;

import com.likelion.session.dto.BoardCreateRequest;
import com.likelion.session.dto.BoardResponse;
import com.likelion.session.dto.BoardUpdateRequest;
import com.likelion.session.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.likelion.session.dto.BoardPageResponse;
import com.likelion.session.dto.DeleteLogResponse;

import java.util.List;

@RestController // REST API 컨트롤러 (JSON 형태로 응답 반환)
@RequestMapping("/boards") // 공통 URL 경로 설정 (/boards로 시작)
@RequiredArgsConstructor // final 필드에 대한 생성자 자동 생성 (의존성 주입용)
public class BoardController {

    private final BoardService boardService;

    /*
        게시글 생성

        [요청 흐름]
        Client
        -> DispatcherServlet
        -> HandlerMapping
        -> BoardController의 create() 메서드 선택
        -> Service 호출
        -> Repository 호출
        -> DB 저장
        -> 결과 반환
        -> JSON 응답
     */
    @Operation( // API 문서용 어노테이션 (Swagger/OpenAPI에서 설명 추가)
            summary = "게시글 생성",
            description = "새로운 게시글을 생성합니다."
    )
    @PostMapping // POST 요청 처리 (데이터 생성)
    public ResponseEntity<BoardResponse> create(@RequestBody BoardCreateRequest request) { // 요청 본문(JSON)을 객체로 변환
        BoardResponse response = boardService.create(request);
        return ResponseEntity.ok(response);
    }

    // 게시글 전체 조회
    @Operation( // API 문서용 어노테이션 (Swagger/OpenAPI에서 설명 추가)
            summary = "게시글 전체 조회",
            description = "등록된 모든 게시글을 조회합니다."
    )
    @GetMapping // GET 요청 처리 (데이터 조회)
    public ResponseEntity<List<BoardResponse>> findAll() {
        List<BoardResponse> response = boardService.findAll();
        return ResponseEntity.ok(response);
    }

    // 게시글 단건 조회
    @Operation( // API 문서용 어노테이션 (Swagger/OpenAPI에서 설명 추가)
            summary = "게시글 단건 조회",
            description = "id로 특정 게시글을 조회합니다."
    )
    @GetMapping("/{id}") // GET 요청 처리 (데이터 조회)
    public ResponseEntity<BoardResponse> findById(@PathVariable Long id) { // URL 경로에 있는 값을 변수로 받음(/boards/{id})
        BoardResponse response = boardService.findById(id);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @Operation( // API 문서용 어노테이션 (Swagger/OpenAPI에서 설명 추가)
            summary = "게시글 수정",
            description = "id로 특정 게시글의 제목과 내용을 수정합니다."
    )
    @PutMapping("/{id}") // PUT 요청 처리 (데이터 수정)
    public ResponseEntity<BoardResponse> update(@PathVariable Long id, //URL의 {id} 값을 추출하는 어노테이션
                                                @RequestBody BoardUpdateRequest request) { // JSON 본문을 Java 객체로 자동 변환해줌.
        BoardResponse response = boardService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @Operation(  // API 문서용 어노테이션 (Swagger/OpenAPI에서 설명 추가)
            summary = "게시글 삭제",
            description = "id로 특정 게시글을 삭제합니다."
    )
    @DeleteMapping("/{id}") // 데이터 삭제
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // BoardController.java 에 아래 메서드들 추가

    // 제목으로 검색
    @Operation(
            summary = "제목으로 게시글 검색",
            description = "제목에 키워드가 포함된 게시글을 조회합니다."
    )
    @GetMapping("/search/title")
    public ResponseEntity<List<BoardResponse>> searchByTitle(@RequestParam String keyword) {
        List<BoardResponse> response = boardService.searchByTitle(keyword);
        return ResponseEntity.ok(response);
    }

    // 작성자로 검색
    @Operation(
            summary = "작성자로 게시글 검색",
            description = "특정 작성자의 게시글을 조회합니다."
    )
    @GetMapping("/search/writer")
    public ResponseEntity<List<BoardResponse>> searchByWriter(@RequestParam String writer) {
        List<BoardResponse> response = boardService.searchByWriter(writer);
        return ResponseEntity.ok(response);
    }

    // 통합 검색
    @Operation(
            summary = "게시글 통합 검색",
            description = "제목 또는 내용에 키워드가 포함된 게시글을 조회합니다."
    )
    @GetMapping("/search")
    public ResponseEntity<List<BoardResponse>> search(@RequestParam String keyword) {
        List<BoardResponse> response = boardService.search(keyword);
        return ResponseEntity.ok(response);
    }

    // 페이지네이션 전체 조회
    @Operation(
            summary = "게시글 페이지 조회",
            description = "페이지네이션이 적용된 게시글 목록을 조회합니다."
    )
    @GetMapping("/page")
    public ResponseEntity<BoardPageResponse> findAllWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        BoardPageResponse response = boardService.findAllWithPage(page, size);
        return ResponseEntity.ok(response);
    }


    // 삭제 이력 조회
    @Operation(summary = "삭제 이력 조회", description = "삭제된 게시글의 이력을 조회합니다.")
    @GetMapping("/delete-logs")
    public ResponseEntity<List<DeleteLogResponse>> findAllDeleteLogs() {
        return ResponseEntity.ok(boardService.findAllDeleteLogs());
    }

}
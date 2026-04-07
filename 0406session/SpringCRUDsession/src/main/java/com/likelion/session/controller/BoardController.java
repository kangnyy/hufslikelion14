package com.likelion.session.controller;

import com.likelion.session.dto.BoardCreateRequest;
import com.likelion.session.dto.BoardResponse;
import com.likelion.session.dto.BoardUpdateRequest;
import com.likelion.session.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
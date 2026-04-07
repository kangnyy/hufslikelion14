package com.likelion.session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter // 모든 필드의 getter 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 포함한 생성자 생성
@Builder // 빌더 패턴 생성 (객체를 유연하게 생성 가능)
public class BoardResponse {

    // 돌려주고 싶은 응답: id, title, content, writer, createdAt, updatedAt
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
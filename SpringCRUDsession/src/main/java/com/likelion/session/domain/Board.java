package com.likelion.session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter // getter 메서드 자동 생성
@Entity // 해당 클래스 DB 테이블로 인식하고 관리
@Table(name = "boards") // 실제 DB에 생성될 테이블 이름 지정. 없으면 클래스 이름(Board)이 테이블 이름이 됨.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성 (protected 접근 제한)
@AllArgsConstructor     // 모든 필드를 포함한 생성자 생성
public class Board {

    @Id // 이 필드가 테이블의 기본키(Primary Key)임을 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // id 값을 DB가 자동으로 1, 2, 3... 순서대로 증가(auto increment)시켜 직접 1, 2, 3, ..을 입력할 필요 없음
    private Long id;

    // @Column()  해당컬럼의 옵션 설정
    // 게시글 제목
    @Column(nullable = false, length = 100) // null 불가 + 최대 길이 100
    private String title;

    // 게시글 내용
    @Column(nullable = false, columnDefinition = "TEXT")  // null 불가 + TEXT 타입으로 지정
    private String content;

    // 작성자
    @Column(nullable = false, length = 30) // null 불가 + 최대 길이 30
    private String writer;

    // 생성 시간
    @Column(nullable = false)   // null 불가
    private LocalDateTime createdAt;

    // 수정 시간
    @Column(nullable = false) // null 불가
    private LocalDateTime updatedAt;


    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @PrePersist  // 엔티티 저장 전에 실행되는 메서드
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate // 엔티티 수정 직전에 실행되는 메서드
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
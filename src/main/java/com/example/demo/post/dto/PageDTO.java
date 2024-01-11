package com.example.demo.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageDTO<T> implements Serializable {
    private List<T> content;
    private int totalPages;
    private long totalElements;

    public PageDTO(List<T> content, int totalPages, long totalElements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    // PageImpl 객체를 PageDTO로 변환하는 스태틱 메소드
    public static <T> PageDTO<T> fromPage(Page<T> page) {
        return new PageDTO<>(page.getContent(), page.getTotalPages(), page.getTotalElements());
    }

}


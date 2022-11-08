package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity //JPA에서 객체로 다루겠다는 의미
@Table(name = "article")
@NoArgsConstructor
@Getter
public class Article {

    @Id //PrimaryKey 역할
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrease 기능 (id를 직접 입력할 경우는 제외한다)
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
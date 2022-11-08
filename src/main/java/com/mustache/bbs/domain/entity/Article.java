package com.mustache.bbs.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //JPA에서 객체로 다루겠다는 의미
@NoArgsConstructor
@Getter
public class Article {

    @Id //PrimaryKey 역할
    @GeneratedValue // AutoIncrease 기능 (id를 직접 입력할 경우는 제외한다)
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
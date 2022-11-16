package com.mustache.bbs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String author; //댓글 작성자
    @Column
    private String comment; //댓글 내용
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article; // 게시물

    public ArticleComment(Article article, String author, String comment) {
        this.article = article;
        this.author = author;
        this.comment = comment;
    }
}
package com.mustache.bbs.domain.dto;


import com.mustache.bbs.domain.entity.Article;
import com.mustache.bbs.domain.entity.ArticleComment;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ArticleCommentDto {
    private Long id;

    private Article article;
    private String author;
    private String comment;

    public ArticleCommentDto(String author, String comment) {
        this.author = author;
        this.comment = comment;
    }

    public ArticleComment toEntity() {
        return new ArticleComment(article, author, comment);
    }
}



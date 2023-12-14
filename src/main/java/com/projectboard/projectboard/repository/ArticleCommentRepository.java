package com.projectboard.projectboard.repository;

import com.projectboard.projectboard.domain.Article;
import com.projectboard.projectboard.domain.ArticleComment;
import com.projectboard.projectboard.domain.QArticle;
import com.projectboard.projectboard.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment> {

    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        // 검색이 되는 필드
        bindings.including(root.content, root.createdAt, root.createdBy);

        // Exact Match 룰 변경
        // bindings.bind(root.title).first(((path, value) -> path.eq(value)));
            // 검색 파라미터를 하나만 받기 때문에 first()
            // 안에는 람다식
            // replace lambda with method reference 가능함. 그러나 그거 대신 아래 StringExpression 사용.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}

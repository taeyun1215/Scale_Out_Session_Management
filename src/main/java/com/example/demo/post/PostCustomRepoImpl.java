package com.example.demo.post;

import com.example.demo.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCustomRepoImpl implements PostCustomRepo {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        QPost post = QPost.post;
        QUser user = QUser.user;

        // Fetching the content
        List<Post> posts = jpaQueryFactory.selectFrom(post)
                .leftJoin(post.user, user)
                .fetchJoin()
                .orderBy(post.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // Fetching the count
        Long count = jpaQueryFactory.select(post.count())
                .from(post)
                .leftJoin(post.user, user)
                .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }
}

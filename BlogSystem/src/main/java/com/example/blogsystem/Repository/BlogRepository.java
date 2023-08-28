package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBlogById(Integer id);

    @Query("select b from Blog b where b.user.id=?1")
    List<Blog> findBlogsByUser(Integer user_id);

    @Query("select b from Blog b where b.user.id=?1 and b.id=?2")
    Blog findBlogByUserAndId(Integer user_id, Integer blog_id);

    @Query("select b from Blog b where b.user.id=?1 and b.title=?2")
    Blog findBlogByUserAndTitle(Integer user_id, String title);
}

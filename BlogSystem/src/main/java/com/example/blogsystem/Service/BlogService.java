package com.example.blogsystem.Service;

import com.example.blogsystem.Api.ApiException;
import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.AuthRepository;
import com.example.blogsystem.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public void addBlog(Integer user_id, Blog blog) {
        User user = authRepository.findUserById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer user_id, Integer blog_id, Blog blog){
        Blog oldBlog = blogRepository.findBlogByUserAndId(user_id, blog_id);
        if (oldBlog == null) throw new ApiException("you don't have authority to modify this blog.");
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setBody(blog.getBody());
        blogRepository.save(oldBlog);
    }

    public void deleteBlog(Integer user_id, Integer blog_id) {
        Blog oldBlog = blogRepository.findBlogByUserAndId(user_id, blog_id);
        if (oldBlog == null) throw new ApiException("you don't have authority to delete this blog.");
        blogRepository.delete(oldBlog);
    }

    public List<Blog> getUserBlogs (Integer user_id) {
        List<Blog> blogs = blogRepository.findBlogsByUser(user_id);
        if (blogs.isEmpty()) throw new ApiException("no blogs yet");
        return blogs;
    }

    public Blog getBlogById(Integer user_id, Integer blog_id) {
        Blog blog = blogRepository.findBlogByUserAndId(user_id, blog_id);
        if (blog == null) throw new ApiException("you don't have authority to access this blog");
        return blog;
    }

    public Blog getBlogByTitle(Integer user_id, String title) {
        Blog blog = blogRepository.findBlogByUserAndTitle(user_id, title);
        if (blog == null) throw new ApiException("no blog of this title");
        return blog;
    }

    public void assignBlogToUser(Integer user_id, Integer blog_id) {
        User user = authRepository.findUserById(user_id);
        Blog blog = blogRepository.findBlogById(blog_id);

        if (user == null || blog == null)
            throw new ApiException("something went wrong");

        blog.setUser(user);
        blogRepository.save(blog);
    }

}

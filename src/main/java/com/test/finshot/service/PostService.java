package com.test.finshot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.finshot.domain.Post;
import com.test.finshot.mapper.PostMapper;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;
    
    public List<Post> getAllPosts() {
        return postMapper.findAll();
    }
    
    public Post getPost(Integer id) {
        postMapper.increaseViewCount(id);
        return postMapper.findById(id);
    }
    
    public boolean createPost(Post post) {
        return postMapper.insert(post) > 0;
    }
    
    public boolean updatePost(Post post, String inputPassword) {
        Post existing = postMapper.findById(post.getId());
        if (existing == null || !existing.getPassword().equals(inputPassword)) {
            return false;
        }
        post.setPassword(existing.getPassword());
        return postMapper.update(post) > 0;
    }
    
    public boolean deletePost(Integer id, String inputPassword) {
        Post existing = postMapper.findById(id);
        if (existing == null || !existing.getPassword().equals(inputPassword)) {
            return false;
        }
        return postMapper.markDeleted(id) > 0;
    }
}

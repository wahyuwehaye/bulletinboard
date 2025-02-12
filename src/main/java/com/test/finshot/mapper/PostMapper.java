package com.test.finshot.mapper;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.test.finshot.domain.Post;

@Mapper
public interface PostMapper {
    
    List<Post> findAll();
    
    Post findById(Integer id);
    
    int insert(Post post);
    
    int update(Post post);
    
    int increaseViewCount(Integer id);
    
    int markDeleted(Integer id);
    
}

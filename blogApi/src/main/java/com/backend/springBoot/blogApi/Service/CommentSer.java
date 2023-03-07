package com.backend.springBoot.blogApi.Service;

import com.backend.springBoot.blogApi.PlayLoads.CommentDTO;

public interface CommentSer {
	CommentDTO createComment(CommentDTO commentDto,Integer postId);
	void deleteComment(Integer commentId);

}

package com.jks.jpa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jks.jpa.exception.ClassNotFoundException;
import com.jks.jpa.model.Post;
import com.jks.jpa.repository.PostRepository;



@RestController
public class PostController {
	@Autowired
	private PostRepository post;
	
	@GetMapping(value="/posts")
	public Page<Post> getAllPost(Pageable pageable){
		return post.findAll(pageable);
		
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/posts")
	public Post savePost(@Valid @RequestBody Post posts) {
		return post.save(posts);
		
	}
	
	@PutMapping("posts/{postId}")
	public Post updatePost(@PathVariable Long postId ,@Valid @RequestBody Post PostRequest) {
		return post.findById(postId).map(po->{
			po.setTitle(PostRequest.getTitle());
			po.setDescription(PostRequest.getDescription());
			po.setContent(PostRequest.getContent());
			return post.save(po);
			
		}).orElseThrow(()->new ClassNotFoundException("postId :"+postId+" not found"));
		
	}
	
	@DeleteMapping("posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId){
		
		return post.findById(postId).map(p->{
			post.delete(p);
			return ResponseEntity.ok().build();
		}).orElseThrow(()->new ClassNotFoundException("postId :"+postId+" not found"));
		
	}
	
	

}

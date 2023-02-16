package com.example.akamaidemo.controller;

import com.example.akamaidemo.dto.SocialNetworkPostDto;
import com.example.akamaidemo.exception.SocialNetworkPostsException;
import com.example.akamaidemo.service.impl.SocialNetworkPostServiceImpl;
import com.example.akamaidemo.utility.ApiPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.PostsCtrl.CTRL)
public class SocialNetworkPostController {

	private static final Logger logger = LoggerFactory.getLogger(SocialNetworkPostController.class);
	@Autowired
	private final SocialNetworkPostServiceImpl postService;

	public SocialNetworkPostController(SocialNetworkPostServiceImpl postService) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<List<SocialNetworkPostDto>> getAllPosts() {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocialNetworkPostDto> getPostById(@PathVariable(value = "id", required = true) Long id) {
		try {
			SocialNetworkPostDto socialNetworkPostDto = postService.findById(id);
			return ResponseEntity.ok(postService.findById(id));
		}catch (Exception error){
			logger.error("Post [" +id+ "] doesn't exist");
			throw new SocialNetworkPostsException(id);
		}
	}

	@PostMapping(value = "/create")
	public ResponseEntity<SocialNetworkPostDto> createSocialNetworkPost(@Valid @RequestBody SocialNetworkPostDto post) {
		try{
			return ResponseEntity.ok(postService.save(post));
		} catch (Exception error){
			logger.error("Unvalid Body Type!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(post);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<SocialNetworkPostDto> updatePost(@PathVariable(value = "id", required = true) Long id, @RequestBody SocialNetworkPostDto post) {
		try {
			SocialNetworkPostDto socialNetworkPost = postService.findById(id);
			return ResponseEntity.ok(postService.update(id, post));
		} catch (Exception error) {
			logger.error("Post [" +id+ "] doesn't exist");
			throw new SocialNetworkPostsException(id);
		}
	}

	@GetMapping("/top10")
	public ResponseEntity<List<SocialNetworkPostDto>> getTop10Posts(){
		List<SocialNetworkPostDto> socialNetworkPostDtos = postService.findAll();
		if (socialNetworkPostDtos.size() < 1){
			throw new SocialNetworkPostsException();
		}
		return ResponseEntity.ok(postService.findTop10ByOrderByViewCountDescPostDateAsc());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id", required = true) Long id){
		SocialNetworkPostDto socialNetworkPost = postService.findById(id);
		if (socialNetworkPost == null){
			logger.error("Post [" +id+ "] doesn't exist");
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(postService.deleteById(id));
	}
}

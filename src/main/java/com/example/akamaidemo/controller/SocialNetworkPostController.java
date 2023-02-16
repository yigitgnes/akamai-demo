package com.example.akamaidemo.controller;

import com.example.akamaidemo.dto.SocialNetworkPostDto;
import com.example.akamaidemo.service.impl.SocialNetworkPostServiceImpl;
import com.example.akamaidemo.utility.ApiPaths;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.PostsCtrl.CTRL)
public class SocialNetworkPostController {

	private final SocialNetworkPostServiceImpl postService;


	public SocialNetworkPostController(SocialNetworkPostServiceImpl postService, ModelMapper modelMapper) {
		this.postService = postService;
	}

	@GetMapping
	public ResponseEntity<List<SocialNetworkPostDto>> getAllPosts() {
		return ResponseEntity.ok(postService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SocialNetworkPostDto> getPostById(@PathVariable(value = "id", required = true) Long id) {
		SocialNetworkPostDto socialNetworkPostDto = postService.findById(id);
		return ResponseEntity.ok(socialNetworkPostDto);
	}

	@PostMapping("/posts")
	public ResponseEntity<SocialNetworkPostDto> createPost(@RequestBody SocialNetworkPostDto post) {
		SocialNetworkPostDto socialNetworkPostDto = postService.save(post);
		return ResponseEntity.ok(postService.save(post));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<SocialNetworkPostDto> updatePost(@PathVariable(value = "id", required = true) Long id, @RequestBody SocialNetworkPostDto post) {
		SocialNetworkPostDto socialNetworkPost = postService.findById(id);
		return ResponseEntity.ok(postService.update(id, post));
	}
}

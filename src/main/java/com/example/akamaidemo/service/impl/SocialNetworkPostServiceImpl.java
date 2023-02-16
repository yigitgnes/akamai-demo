package com.example.akamaidemo.service.impl;

import com.example.akamaidemo.dto.SocialNetworkPostDto;
import com.example.akamaidemo.entity.SocialNetworkPost;
import com.example.akamaidemo.exception.SocialNetworkPostsException;
import com.example.akamaidemo.repo.SocialNetworkPostRepository;
import com.example.akamaidemo.service.SocialNetworkPostService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SocialNetworkPostServiceImpl implements SocialNetworkPostService {
	@Autowired
	private final ModelMapper modelMapper;

	@Autowired
	private final SocialNetworkPostRepository postRepository;

	private static final Logger logger = LoggerFactory.getLogger(SocialNetworkPostServiceImpl.class);

	public SocialNetworkPostServiceImpl(@Qualifier("socialPost") SocialNetworkPostRepository postRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.postRepository = postRepository;
	}

	@Override
	public List<SocialNetworkPostDto> findAll() {
		List<SocialNetworkPost> socialNetworkPost = postRepository.findAll();
		return Arrays.asList(modelMapper.map(socialNetworkPost, SocialNetworkPostDto[].class));
	}

	@Override
	public SocialNetworkPostDto findById(Long id) {
		try	{
			SocialNetworkPost socialNetworkPost = postRepository.getById(id);
			return modelMapper.map(socialNetworkPost, SocialNetworkPostDto.class);
		}catch (Exception err) {
			throw new SocialNetworkPostsException(id);
		}
	}

	@Override
	public SocialNetworkPostDto save(SocialNetworkPostDto postDto) {
		if (postDto.getContent() == null) {
			throw new IllegalArgumentException("Content is null!");
		}
		SocialNetworkPost socialNetworkPost = modelMapper.map(postDto, SocialNetworkPost.class);
		socialNetworkPost = postRepository.save(socialNetworkPost);
		return modelMapper.map(socialNetworkPost, SocialNetworkPostDto.class);
	}

	@Override
	public SocialNetworkPostDto update(Long id, SocialNetworkPostDto postDto) {
		SocialNetworkPost socialNetworkPost = postRepository.getById(id);
		if (socialNetworkPost == null) {
			throw new IllegalArgumentException("Post ID [" + id + "] doesn't exist");
		}
		socialNetworkPost.setContent(postDto.getContent());
		socialNetworkPost.setPostDate(postDto.getPostDate());
		socialNetworkPost.setAuthor(postDto.getAuthor());
		socialNetworkPost.setViewCount(postDto.getViewCount());
		postRepository.save(socialNetworkPost);
		logger.info("Post updated successfully");
		return modelMapper.map(socialNetworkPost, SocialNetworkPostDto.class);
	}

	@Override
	public Boolean deleteById(Long id) {
		postRepository.deleteById(id);
		logger.info("Post id: " + id + " has been deleted successfully!");
		return true;
	}

	public List<SocialNetworkPostDto> findTop10ByOrderByViewCountDescPostDateAsc() {
		List<SocialNetworkPost> socialNetworkPosts = postRepository.findTop10ByOrderByViewCountDescPostDateAsc();
		return Arrays.asList(modelMapper.map(socialNetworkPosts, SocialNetworkPostDto[].class));
	}

}

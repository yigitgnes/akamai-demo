package com.example.akamaidemo.service;

import com.example.akamaidemo.dto.SocialNetworkPostDto;
import com.example.akamaidemo.entity.SocialNetworkPost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocialNetworkPostService {
	List<SocialNetworkPostDto> findAll();
	SocialNetworkPostDto findById(Long id);
	SocialNetworkPostDto save(SocialNetworkPostDto postDto);
	Boolean deleteById(Long id);
	List<SocialNetworkPost> findTopTenByOrder();
	SocialNetworkPostDto update(Long id, SocialNetworkPostDto postDto);
}

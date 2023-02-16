package com.example.akamaidemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ControllerAdvice
public class SocialNetworkPostsException extends RuntimeException{

	public SocialNetworkPostsException(Long id) {
		super("Could not find post " + id);
	}

	public SocialNetworkPostsException(){
		super("Could not find any post");
	}
}

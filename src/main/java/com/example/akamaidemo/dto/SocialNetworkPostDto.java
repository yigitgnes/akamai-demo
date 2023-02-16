package com.example.akamaidemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialNetworkPostDto {

	private Long id;

	private Date postDate;

	private String author;

	private String content;

	private Integer viewCount;
}

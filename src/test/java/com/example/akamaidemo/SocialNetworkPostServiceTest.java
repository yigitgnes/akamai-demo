package com.example.akamaidemo;

import com.example.akamaidemo.entity.SocialNetworkPost;
import com.example.akamaidemo.repo.SocialNetworkPostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SocialNetworkPostServiceTest {

	@Autowired
	private SocialNetworkPostRepository socialNetworkPostRepository;

	@Mock
	private SocialNetworkPostRepository socialNetworkPostRepositoryMocker;

	//JUnit test for saving a content
	@Test
	public void saveContentTest() {

		SocialNetworkPost socialNetworkPost = SocialNetworkPost.builder()
				.author("Will Smith")
				.content("New shoots from the movie I, robot")
				.postDate(new Date())
				.viewCount(10)
				.build();
		SocialNetworkPost savedPost = socialNetworkPostRepository.save(socialNetworkPost);

		Assertions.assertThat(savedPost.getId()).isGreaterThan(0);
		Assertions.assertThat(savedPost.getAuthor()).isEqualTo(socialNetworkPost.getAuthor());
		Assertions.assertThat(savedPost.getContent()).isEqualTo(socialNetworkPost.getContent());
		Assertions.assertThat(savedPost.getPostDate()).isEqualTo(socialNetworkPost.getPostDate());
		Assertions.assertThat(savedPost.getViewCount()).isEqualTo(socialNetworkPost.getViewCount());

	}

	//JUnit test for getting a content
	@Test
	public void getContentTest() {

		SocialNetworkPost socialNetworkPost = new SocialNetworkPost(1L, new Date(), "John Doe", "Test get content", 15);

		socialNetworkPostRepository.save(socialNetworkPost);
		SocialNetworkPost foundPost = socialNetworkPostRepository.getById(socialNetworkPost.getId());

		Assertions.assertThat(foundPost.getId()).isEqualTo(socialNetworkPost.getId());
		Assertions.assertThat(foundPost.getAuthor()).isEqualTo(socialNetworkPost.getAuthor());
		Assertions.assertThat(foundPost.getContent()).isEqualTo(socialNetworkPost.getContent());
		Assertions.assertThat(foundPost.getPostDate()).isEqualTo(socialNetworkPost.getPostDate());
		Assertions.assertThat(foundPost.getViewCount()).isEqualTo(socialNetworkPost.getViewCount());
	}

	//JUnit test for getting all contents
	@Test
	public void getAllContentsTest() {

		SocialNetworkPost post1 = new SocialNetworkPost(1L, new Date(), "John Doe", "Test get content", 15);
		SocialNetworkPost post2 = new SocialNetworkPost(2L, new Date(), "George Doe", "Test get content", 20);
		SocialNetworkPost post3 = new SocialNetworkPost(3L, new Date(), "Jack Doe", "Test get content", 23);

		List<SocialNetworkPost> posts = Arrays.asList(post1, post2, post3);

		Mockito.when(socialNetworkPostRepositoryMocker.findAll()).thenReturn(posts);

		List<SocialNetworkPost> result = socialNetworkPostRepositoryMocker.findAll();

		Assertions.assertThat(result).isEqualTo(posts);
	}

	//JUnit test for updating a content
	@Test
	public void updateContentTest() {

		SocialNetworkPost socialNetworkPost = new SocialNetworkPost(1L, new Date(), "Will Smith", "New shoots from the movie I, robot", 15);

		socialNetworkPost.setContent("Made a mistake! Shoots belong to Troy movie");
		socialNetworkPost.setAuthor("Brad Pitt");
		socialNetworkPost.setPostDate(new Date());

		SocialNetworkPost updatedSocialNetworkPost = socialNetworkPostRepository.save(socialNetworkPost);

		Assertions.assertThat(updatedSocialNetworkPost.getContent()).isEqualTo(socialNetworkPost.getContent());
		Assertions.assertThat(updatedSocialNetworkPost.getAuthor()).isEqualTo(socialNetworkPost.getAuthor());
		Assertions.assertThat(updatedSocialNetworkPost.getPostDate()).isEqualTo(socialNetworkPost.getPostDate());
	}

	@Test
	public void getTop10RecodsTest() {
		SocialNetworkPost post1 = new SocialNetworkPost(1L, new Date(), "John Doe", "Test content 1", 20);
		SocialNetworkPost post2 = new SocialNetworkPost(2L, new Date(), "George Doe", "Test content 2", 30);
		SocialNetworkPost post3 = new SocialNetworkPost(3L, new Date(), "Jack Doe", "Test content 3", 40);
		SocialNetworkPost post4 = new SocialNetworkPost(4L, new Date(), "John Doe", "Test content 1", 50);
		SocialNetworkPost post5 = new SocialNetworkPost(5L, new Date(), "George Doe", "Test content 2", 60);
		SocialNetworkPost post6 = new SocialNetworkPost(6L, new Date(), "Jack Doe", "Test content 3", 70);
		SocialNetworkPost post7 = new SocialNetworkPost(7L, new Date(), "John Doe", "Test content 1", 80);
		SocialNetworkPost post8 = new SocialNetworkPost(8L, new Date(), "George Doe", "Test content 2", 90);
		SocialNetworkPost post9 = new SocialNetworkPost(9L, new Date(), "Jack Doe", "Test content 3", 100);
		SocialNetworkPost post10 = new SocialNetworkPost(10L, new Date(), "John Doe", "Test content 1", 200);
		SocialNetworkPost post11 = new SocialNetworkPost(11L, new Date(), "George Doe", "Test content 2", 300);
		SocialNetworkPost post12 = new SocialNetworkPost(12L, new Date(), "Jack Doe", "Test content 3", 400);
		socialNetworkPostRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5, post6, post7, post8, post9, post10, post11, post12));

		List<SocialNetworkPost> topPosts = socialNetworkPostRepository.findTop10ByOrderByViewCountDescPostDateAsc();
		Assertions.assertThat(topPosts).hasSize(10);
		Assertions.assertThat(topPosts.get(0)).isEqualTo(post12);
		Assertions.assertThat(topPosts.get(1)).isEqualTo(post11);
		Assertions.assertThat(topPosts.get(2)).isEqualTo(post10);
		Assertions.assertThat(topPosts.get(3)).isEqualTo(post9);
		Assertions.assertThat(topPosts.get(4)).isEqualTo(post8);
		Assertions.assertThat(topPosts.get(5)).isEqualTo(post7);
		Assertions.assertThat(topPosts.get(6)).isEqualTo(post6);
		Assertions.assertThat(topPosts.get(7)).isEqualTo(post5);
		Assertions.assertThat(topPosts.get(8)).isEqualTo(post4);
		Assertions.assertThat(topPosts.get(9)).isEqualTo(post3);
	}
}

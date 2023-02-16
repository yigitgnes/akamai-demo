package com.example.akamaidemo.repo;

import com.example.akamaidemo.entity.SocialNetworkPost;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("socialPost")
@Repository
public interface SocialNetworkPostRepository extends JpaRepository<SocialNetworkPost, Long> {

}

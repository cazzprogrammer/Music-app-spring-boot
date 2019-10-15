package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music,Integer> {
}

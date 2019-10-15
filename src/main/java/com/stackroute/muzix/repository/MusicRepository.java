package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music,Integer> {

     @Query("FROM Music WHERE trackName= ?1")
     List<Music> getMusicInfoByName(String trackName);
}

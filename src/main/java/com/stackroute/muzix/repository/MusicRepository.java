package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Music;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends MongoRepository<Music,Integer> {

     @Query("{'trackName' : ?0}")
     List<Music> getMusicInfoByName(String trackName);
}

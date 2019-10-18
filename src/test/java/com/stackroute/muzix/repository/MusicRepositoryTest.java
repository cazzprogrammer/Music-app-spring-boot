package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Music;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class MusicRepositoryTest {

    @Autowired
    private MusicRepository musicRepository;
    private Music music;

    @Before
    public void setUp()
    {
        music = new Music();
        music.setTrackId(10);
        music.setTrackName("John");
        music.setComments("Jenny");

    }

//    @After
//    public void tearDown(){
//
//        musicRepository.deleteAll();
//    }
    @Test
    public void testSavemusic(){
        musicRepository.save(music);
        Music fetchmusic = musicRepository.findById(music.getTrackId()).get();
        Assert.assertEquals(10,fetchmusic.getTrackId());

    }

    @Test
    public void testSavemusicFailure(){
        Music testmusic = new Music(1,"hsss","as");
        musicRepository.save(music);
        Music fetchmusic = musicRepository.findById(music.getTrackId()).get();
        Assert.assertNotSame(testmusic,music);
    }
    


}

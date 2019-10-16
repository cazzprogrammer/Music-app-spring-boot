package com.stackroute.muzix.seeder;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    MusicService musicService;

    @Autowired
    public DataLoader(MusicService musicService)
    {
        this.musicService = musicService;
    }

    @Override
    public void run(String... args) throws Exception {

        musicService.saveUserAuto(new Music(1,"hwk","sad"));
        musicService.saveUserAuto(new Music(2,"ds","sad"));

    }
}

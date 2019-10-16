package com.stackroute.muzix.seeder;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Listener implements ApplicationListener<ContextRefreshedEvent> {
    MusicService musicService;

    @Autowired
    public Listener(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        musicService.saveUserListen(new Music(3,"sas","sads"));
    }
}

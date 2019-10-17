package com.stackroute.muzix.seeder;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Listener implements ApplicationListener<ContextRefreshedEvent> {
    MusicService musicService;

    @Value("${id}")
    int id;
    @Value("${name}")
    String name;
    @Value("${comment}")
    String comment;
    @Autowired
    Environment environment;

    @Autowired
    public Listener(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        musicService.saveUserListen(new Music(3,"sas","sads"));
        musicService.saveUserListen(new Music(id,name,comment));
        musicService.saveUserListen(new Music(Integer.parseInt((environment.getProperty("id1"))),environment.getProperty("name1"),environment.getProperty("comment1")));
    }
}

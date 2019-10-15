package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Music;

import java.util.List;

public interface MusicService {

    public Music saveUser(Music user);
    public Music updateUser(Music user);
    public void deleteUser(int trackId);
    public Music findById(int trackId);
    public List<Music> getAllUsers();
    public List<Music> queryString(String name);


}

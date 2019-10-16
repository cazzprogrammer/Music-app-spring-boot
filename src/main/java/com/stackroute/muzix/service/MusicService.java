package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;

import java.util.List;

public interface MusicService {

    public Music saveUser(Music user);
    public Music updateUser(Music user) throws TrackAlreadyExistsException;
    public void deleteUser(int trackId) throws TrackNotFoundException;
    public Music findById(int trackId) throws TrackNotFoundException;
    public List<Music> getAllUsers();
    public List<Music> queryString(String name) throws TrackNotFoundException;


}

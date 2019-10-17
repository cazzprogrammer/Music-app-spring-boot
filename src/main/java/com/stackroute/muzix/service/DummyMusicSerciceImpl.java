package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class DummyMusicSerciceImpl implements MusicService{
    MusicRepository musicRepository;

    @Autowired
    public DummyMusicSerciceImpl(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    @Override
    public Music saveUser(Music user) {
        Music savedUser = musicRepository.save(user);
        System.out.println("heklo");
        return savedUser;
    }

    @Override
    public Music updateUser(Music user) throws TrackAlreadyExistsException {
        Optional<Music> user1 = musicRepository.findById(user.getTrackId());

        Music user2 = user1.get();
        user2.setTrackName(user.getTrackName());
        user2.setComments(user.getComments());

        return musicRepository.save(user2);
    }

    @Override
    public void deleteUser(int trackId) throws TrackNotFoundException {
        Optional<Music> user1 = musicRepository.findById(trackId);
        Music user2 = user1.get();

        musicRepository.delete(user2);
    }

    @Override
    public Music findById(int trackId) throws TrackNotFoundException {
        Optional<Music> userId = musicRepository.findById(trackId);
        System.out.println(userId.toString());
        Music user2 = userId.get();
        return user2;
    }

    @Override
    public List<Music> getAllUsers() {
        return musicRepository.findAll();
    }

    @Override
    public List<Music> queryString(String name) throws TrackNotFoundException {
        List<Music> list = musicRepository.getMusicInfoByName(name);
        return list;
    }

    @Override
    public Music saveUserAuto(Music music) {
        Music music1 = musicRepository.save(music);
        return music1;
    }

    @Override
    public Music saveUserListen(Music music) {
        Music music1 = musicRepository.save(music);
        return music1;
    }
}
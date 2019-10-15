package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicServiceImpl implements MusicService {
    MusicRepository musicRepository;
    @Autowired
    public MusicServiceImpl(MusicRepository musicRepository)
    {
        this.musicRepository = musicRepository;
    }
    @Override
    public Music saveUser(Music user) {
        Music savedUser = musicRepository.save(user);
        return savedUser;
    }

    @Override
    public Music updateUser(Music user) {
        Optional<Music> user1 = musicRepository.findById(user.getTrackId());

        Music user2 = user1.get();
        user2.setTrackName(user.getTrackName());
        user2.setComments(user.getComments());

        return musicRepository.save(user2);
    }

    @Override
    public void deleteUser(int trackId) {
        Optional<Music> user1 = musicRepository.findById(trackId);
        Music user2 = user1.get();
        musicRepository.delete(user2);
    }

    @Override
    public Music findById(int trackId) {
        Optional<Music> userId = musicRepository.findById(trackId);
        Music user2 = userId.get();
        return user2;
    }

    @Override
    public List<Music> getAllUsers() {
        return musicRepository.findAll();
    }
}

package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.repository.MusicRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MusicServiceTest {

    private Music music;

    //Create a mock for UserRepository
    @Mock
    private MusicRepository musicRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private MusicServiceImpl musicService;
    List<Music> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        music = new Music();
        music.setTrackId(101);
        music.setTrackName("John");
        music.setComments("Jenny is good girl");

        list = new ArrayList<>();
        list.add(music);


    }

    @Test
    public void saveUserTestSuccess() throws TrackAlreadyExistsException {

        when(musicRepository.save((Music) any())).thenReturn(music);
        Music savedUser = musicService.saveUser(music);
        Assert.assertEquals(music,savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(musicRepository,times(1)).save(music);

    }

    @Test
    public void getAllUser(){

        musicRepository.save(music);
        //stubbing the mock to return specific data
        when(musicRepository.findAll()).thenReturn(list);
        List<Music> userlist = musicService.getAllUsers();
        Assert.assertEquals(list,userlist);
    }
}

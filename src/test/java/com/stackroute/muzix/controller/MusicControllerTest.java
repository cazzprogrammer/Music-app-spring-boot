package com.stackroute.muzix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.MusicService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Music music;

    @MockBean
    private MusicService musicService;

    @InjectMocks
    private MusicController musicController;

    private List<Music> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(musicController).build();
        music = new Music();
        music.setTrackId(26);
        music.setTrackName("Jonny");
        music.setComments("hello world");
        list = new ArrayList();
        list.add(music);
    }

    @Test
    public void saveUser() throws Exception {
        when(musicService.saveUser(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void saveUserFailure() throws Exception {
        when(musicService.saveUser(any())).thenThrow(TrackAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
              .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateUser() throws Exception {
        when(musicService.updateUser(any())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v2/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void updateUserFailure() throws Exception {
        when(musicService.updateUser(any())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v2/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getAllUsers() throws Exception {
        when(musicService.getAllUsers()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/music")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void deleteUser() throws Exception {
        when(musicService.deleteUser(list.get(0).getTrackId())).thenReturn(music);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/music/"+list.get(0).getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteUserFailure() throws Exception {
        when(musicService.deleteUser(list.get(0).getTrackId())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v2/music/"+list.get(0).getTrackId())
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(music)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }




    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
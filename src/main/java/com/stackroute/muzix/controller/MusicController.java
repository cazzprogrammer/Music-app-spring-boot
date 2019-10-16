package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Music;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v2")
public class MusicController {
    MusicService musicService;

    public MusicController(MusicService musicService)
    {
        this.musicService=musicService;

    }
    @PostMapping("music")
    public ResponseEntity<?> saveUser(@RequestBody Music music)
    {
        ResponseEntity responseEntity ;
        try {
            musicService.saveUser(music);
            responseEntity = new ResponseEntity<String>("Successfull created", HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("music")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<List<Music>>(musicService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("music/{trackId}")
    public ResponseEntity<?> searchByid(@PathVariable(value = "trackId") int trackId)
    {   ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Music>(musicService.findById(trackId), HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("music")
    public ResponseEntity<?> updateinfo(@RequestBody Music user)
    {
        ResponseEntity responseEntity ;
        try {
            musicService.updateUser(user);
            responseEntity = new ResponseEntity<String>("updated successfull", HttpStatus.CREATED);
        }
        catch (TrackAlreadyExistsException ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;


    }
    @DeleteMapping("music/{trackId}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "trackId") int trackId)
    {
        ResponseEntity responseEntity ;
        try {
            musicService.deleteUser(trackId);
            responseEntity = new ResponseEntity<String>("deleted successfull", HttpStatus.CREATED);
        }
        catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }

    @GetMapping("music/find/{trackName}")
    public ResponseEntity<?> query(@PathVariable(value = "trackName") String trackName)
    {
        ResponseEntity responseEntity ;
        try{
            responseEntity = new ResponseEntity<List<Music>>( musicService.queryString(trackName),HttpStatus.OK);
        }
        catch (TrackNotFoundException ex)
        {
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}

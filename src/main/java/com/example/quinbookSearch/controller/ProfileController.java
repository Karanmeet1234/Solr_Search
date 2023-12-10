package com.example.quinbookSearch.controller;


import com.example.quinbookSearch.dto.ProfileDto;
import com.example.quinbookSearch.entity.Profile;
import com.example.quinbookSearch.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quinbook")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @GetMapping
    public List<ProfileDto> getAllProducts() {
        return profileService.display();
    }
    @PostMapping("/addprofile")
    public void saveProduct(@RequestBody ProfileDto profileDto){
        profileService.saveProfile(profileDto);
    }

    @GetMapping("/search")
    public List<Profile> getByName(@RequestParam String text){

        return profileService.findName(text);
    }


}

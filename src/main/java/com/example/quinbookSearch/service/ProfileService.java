package com.example.quinbookSearch.service;

import com.example.quinbookSearch.dto.ProfileDto;
import com.example.quinbookSearch.entity.Profile;
import com.example.quinbookSearch.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

   @Autowired
    private ProfileRepository profileRepository;


    public void saveProfile(ProfileDto profileDto) {

       Profile profile = new Profile();
       profile.setUserId(profileDto.getUserId());
       profile.setName(profileDto.getName());
       profile.setBio(profileDto.getBio());
       profileRepository.save(profile);
    }

    public List<ProfileDto> display() {
        Iterable<Profile> profile = profileRepository.findAll();
        List<ProfileDto> profileDtos = new ArrayList<>();
        profile.forEach(profilee -> {
            ProfileDto profileDto = ProfileDto.builder().
                    userId(profilee.getUserId()).
                    name(profilee.getName()).
                    bio(profilee.getBio()).build();
            profileDtos.add(profileDto);
        });
        return profileDtos;
    }

    public List<Profile> findName(String text){
        String searchText = "";
        for(int i=0;i<text.length();i++){
            searchText += text.charAt(i) + "*";
        }
        return profileRepository.findName(searchText);

    }

}
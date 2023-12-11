package com.example.quinbookSearch.service;

import com.example.quinbookSearch.FeignClients.SearchUserFeign;
import com.example.quinbookSearch.dto.ProfileDto;
import com.example.quinbookSearch.dto.ProfileResponseDto;
import com.example.quinbookSearch.entity.Profile;
import com.example.quinbookSearch.repository.ProfileRepository;
import org.springframework.beans.BeanUtils;
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

   @Autowired
   private SearchUserFeign searchUserFeign;


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

    public List<ProfileResponseDto> findName(String text, String currentId){
        String searchText = "";
        for(int i=0;i<text.length();i++){
            searchText += text.charAt(i) + "*";
        }

        List<Profile> searchItem = profileRepository.findName(searchText);
        List<ProfileResponseDto> responseItem = new ArrayList<>();

        for(Profile profile: searchItem){
            String userId = profile.getUserId();
            ProfileResponseDto profileResponseDto=new ProfileResponseDto();
            //!check this this.userId is present in the friend list:
            //check in request List status pending. or else status add friend.
            List<String>Friends=searchUserFeign.getAllFriendsByUserId(currentId);
            List<String>friendRequests=searchUserFeign.getAllFriendRequestByUserId(currentId);
            List<String>sentFriendRequests=searchUserFeign.getAllSentFriendRequestByUserId(currentId);

            if((!(Friends.contains(userId))) && friendRequests.contains(userId)){
                profileResponseDto.setStatus("ACCEPT");
            }
            else if((!(Friends.contains(userId))) && sentFriendRequests.contains(userId)){
                profileResponseDto.setStatus("PENDING");
            }
            else if((!(Friends.contains(userId)))){
                profileResponseDto.setStatus("ADD");
            }
            BeanUtils.copyProperties(profile, profileResponseDto);

            responseItem.add(profileResponseDto);
        }

        return responseItem ;

    }

}
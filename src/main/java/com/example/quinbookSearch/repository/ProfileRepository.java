package com.example.quinbookSearch.repository;

import com.example.quinbookSearch.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProfileRepository extends SolrCrudRepository<Profile, String> {

    @Query("name:*?0* OR bio:*?0*")
    List<Profile> findName(String name);

}

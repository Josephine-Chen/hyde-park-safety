package com.hydeparksafety.repo;

import com.hydeparksafety.entity.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
public interface IncidentRepo extends MongoRepository<Incident, String> {
    List<Incident> findAll();
//    List<Incident>

    Incident save(Incident incident);
}

package com.hydeparksafety.repo;

import com.hydeparksafety.entity.Incident;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HSong on 11/11/2017.
 */
public interface IncidentRepo<I extends  Incident, String extends Serializable>
        extends MongoRepository<Incident, String> {
    List<Incident> findAll();
//    List<Incident> findByOccurredBetween(Date start, Date end);
    List<Incident> findFirst5ByOrderByOccurredDesc();
    Incident save(Incident incident);
}

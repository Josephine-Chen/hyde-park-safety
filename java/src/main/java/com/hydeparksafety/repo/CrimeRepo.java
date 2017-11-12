package com.hydeparksafety.repo;

import com.hydeparksafety.entity.Crime;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by HSong on 11/11/2017.
 */
public interface CrimeRepo extends MongoRepository<Crime, String> {

}

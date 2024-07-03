package com.api.mfb.repository;

import com.api.mfb.model.Servicos;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends MongoRepository <Servicos, ObjectId> {

}

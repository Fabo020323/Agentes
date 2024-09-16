package com.part_of_fa.usuarios_documentos.user.repository;

import com.part_of_fa.usuarios_documentos.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUsername(String username);
}

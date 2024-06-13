package org.rahul.demo.repository;

// import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.rahul.demo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,ObjectId> {

    User findByUserName(String userName);

    void deleteByUserName(String userName);
}

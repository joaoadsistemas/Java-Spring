package com.joaosilveira.workshopmongo.repositories;

import com.joaosilveira.workshopmongo.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post, String> {

    Optional<Post> findById(String id);
    void deleteById(String id);

    // QUERY PROPRIA DO SPRING
    List<Post> findByTitleContainingIgnoreCase(String text);








    // QUERYS PERSONALIZADA
    @Query("{ 'title':  { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    @Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2} }, { $or: [ { 'title':  { $regex: ?0, $options: 'i'" +
            " } }, { 'body':  { $regex: ?0, $options: 'i' } }, { 'comments.text':  { $regex: ?0, $options: 'i' } } ] " +
            "} ] }")
    List<Post> fullSearch(String text, Date minDate, Date maxDate);

}

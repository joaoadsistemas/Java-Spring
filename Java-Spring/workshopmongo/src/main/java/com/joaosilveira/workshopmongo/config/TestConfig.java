package com.joaosilveira.workshopmongo.config;

import com.joaosilveira.workshopmongo.dtos.AuthorDTO;
import com.joaosilveira.workshopmongo.dtos.CommentDTO;
import com.joaosilveira.workshopmongo.entities.Post;
import com.joaosilveira.workshopmongo.entities.User;
import com.joaosilveira.workshopmongo.repositories.PostRepository;
import com.joaosilveira.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        var maria = new User(null, "Maria Brown", "maria@gmail.com");
        var alex = new User(null, "Alex Green", "alex@gmail.com");
        var bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

    var post1 = new Post(null, sdf.parse("21/11/2023"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
            new AuthorDTO(maria));
    var post2 = new Post(null, sdf.parse("23/11/2023"), "Bom dia", "Acordei feliz hoje",
                new AuthorDTO(maria));

    var c1 = new CommentDTO("Boa viagem mano!", Instant.now(), new AuthorDTO(alex));
    var c2 = new CommentDTO("Aproveite!", Instant.now(), new AuthorDTO(bob));
    var c3 = new CommentDTO("Tenha um ótimo dia!", Instant.now(), new AuthorDTO(alex));

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().addAll(Arrays.asList(c3));

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(maria);

    }
}

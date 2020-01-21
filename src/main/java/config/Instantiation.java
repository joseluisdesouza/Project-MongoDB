package config;

import DTO.AuthorDTO;
import DTO.CommentDTO;
import domain.Post;
import domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import repository.PostRepository;
import repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    private UserRepository userRepository;

    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/03/2018"),
                "para sao paulo", "Vou viajar para sao paulo",
                new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"),
                "Bom dia", "Acordei feliz hoje",
                new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem!",
                sdf.parse("21/03/2018"),
                new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!",
                sdf.parse("21/03/2018"),
                new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia!",
                sdf.parse("21/03/2018"),
                new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Arrays.asList(c3));

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
        postRepository.saveAll(Arrays.asList(post1, post2));

    }
}

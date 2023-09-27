import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        LocalDateTime dataHoraEspecifica = LocalDateTime.of(2018, 6, 21, 14, 30, 44);
        LocalDateTime dataHoraEspecifica2 = LocalDateTime.of(2018, 7, 28, 23, 14, 19);
        DateTimeFormatter horaPersonalizada = DateTimeFormatter.ofPattern("dd/MM/yyyy H:m:s");
        Post post1 = new Post();

        post1.setMoment(dataHoraEspecifica);
        post1.setTitle("Traveling to New Zealand");
        post1.setContent("I'm going to visit this wonderful country!");
        post1.setLikes(12);
        Comment comment1 = new Comment("Have a nice trip");
        Comment comment2 = new Comment("Wow that's awesome!");
        post1.addComment(comment1);
        post1.addComment(comment2);

        System.out.println(post1.getTitle());
        System.out.println(post1.getLikes() + " Likes - " + post1.getMoment().format(horaPersonalizada));
        System.out.println(post1.getContent());
        System.out.println("Comments:");
        System.out.println(post1.getComments().get(0).getText());
        System.out.println(post1.getComments().get(1).getText());

        System.out.println();
        System.out.println();

        Post post2 = new Post();

        post2.setMoment(dataHoraEspecifica2);
        post2.setTitle("Good night guys");
        post2.setContent("See you tomorrow");
        post2.setLikes(5);
        Comment comment3 = new Comment("Good night");
        Comment comment4 = new Comment("May the Force be with you");
        post2.addComment(comment3);
        post2.addComment(comment4);

        System.out.println(post2.getTitle());
        System.out.println(post2.getLikes() + " Likes - " + post2.getMoment().format(horaPersonalizada));
        System.out.println(post2.getContent());
        System.out.println("Comments:");
        System.out.println(post2.getComments().get(0).getText());
        System.out.println(post2.getComments().get(1).getText());


    }
}
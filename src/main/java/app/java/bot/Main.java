package app.java.bot;

import app.java.bot.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    QuoteService service;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Code for receiving records. For first run only
        // Unlock same code in BashParser and QuoteService classes
//        var index = service.getIndex();
//        System.out.println(index);
    }
}
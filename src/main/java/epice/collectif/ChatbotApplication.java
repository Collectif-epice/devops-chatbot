package epice.collectif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.io.IOException;

@SpringBootApplication
public class ChatbotApplication extends SpringBootServletInitializer {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(ChatbotApplication.class, args);

        Chatbot bot = new Chatbot();

        bot.connect(System.getenv("bot-token"));
        bot.listen();

	}
}

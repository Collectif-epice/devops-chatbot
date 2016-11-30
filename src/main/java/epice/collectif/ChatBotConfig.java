package epice.collectif;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Created by anthonyrey on 30/11/2016.
 */
@Configuration
public class ChatBotConfig  {

    @Bean
    public Chatbot initBot(){
        Chatbot bot = new Chatbot();

        try {
            bot.connect(System.getenv("bot-token"));
            bot.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bot;
    }
}

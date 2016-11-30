package epice.collectif;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ChatbotApplication.class, args);

        Chatbot bot = new Chatbot();

        bot.connect("xoxb-110200573778-S2M1aOhC1cgSKHPXho7snCcE");
        bot.listen();

	}
}

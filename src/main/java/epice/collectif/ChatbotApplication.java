package epice.collectif;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import com.ullink.slack.simpleslackapi.listeners.SlackMessagePostedListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ChatbotApplication.class, args);

        Properties prop = new Properties();
        prop.load(ChatbotApplication.class.getClassLoader().getResourceAsStream("application.properties"));

        Chatbot bot = new Chatbot();

        bot.connect(prop.getProperty("bot-token"));
        bot.listen();

	}
}

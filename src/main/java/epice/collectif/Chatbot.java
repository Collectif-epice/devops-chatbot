package epice.collectif;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Chatbot {

    private SlackSession session;
    private final String CHANNEL = "bot";
    private final String INPUT = "hello";
    private final String OUTPUT = "Fuck you";


    public void connect(String token) throws IOException {

        session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();
    }

    public void listen() throws IOException {

        Properties prop = new Properties();
        prop.load(ChatbotApplication.class.getClassLoader().getResourceAsStream("application.properties"));

        session.addMessagePostedListener((event, session) -> {

            SlackChannel channel = session.findChannelByName(CHANNEL);

            if (!channel.getId().equals(event.getChannel().getId())) {
                return;
            }

            String messageContent = event.getMessageContent();
            if (messageContent.startsWith("build") && messageContent.contains("chatbot")) {


                session.sendMessage(event.getChannel(),"/build-chatbot");

                /**  Bordel en curl
                URL url = null;
                try {
                    url = new URL(prop.getProperty("build-url"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                    for (String line; (line = reader.readLine()) != null;) {
                        System.out.println(line);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }**/
            

                session.sendMessage(event.getChannel(),"Jenkins job '(BUILD) chatbot' is started");
            }
            else if (messageContent.startsWith("build") && messageContent.contains("core")) {
                session.sendMessage(event.getChannel(),"/build-core");
                session.sendMessage(event.getChannel(),"Jenkins job '(BUILD) core-component' is started");
            }
            else
                return;
        });
    }

}

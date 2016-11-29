package epice.collectif;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

import java.io.IOException;

public class Chatbot {

    private SlackSession session;
    private final String CHANNEL = "bot";
    private final String INPUT = "hello";
    private final String OUTPUT = "Fuck you";


    public void connect(String token) throws IOException {

        session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();
    }

    public void listen(){

        session.addMessagePostedListener((event, session) -> {

            SlackChannel channel = session.findChannelByName(CHANNEL);

            if (!channel.getId().equals(event.getChannel().getId())) {
                return;
            }

            String messageContent = event.getMessageContent();
            if (!messageContent.contains(INPUT)) {
                return;
            }

            session.sendMessage(event.getChannel(),OUTPUT);
        });
    }

}

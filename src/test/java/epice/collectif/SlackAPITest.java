package epice.collectif;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class SlackAPITest {

    private SlackSession session;

    @Value("${bot-token}")
    private String botToken;

    @Before
    public void setUp() throws Exception {

        session = SlackSessionFactory.createWebSocketSlackSession(botToken);
        session.connect();
    }

    @Test
    public void should_pass_if_token_is_auth() throws IOException {

        session.connect();
        session.disconnect();
    }

    @Test
    public void should_pass_if_channel_is_found() throws IOException {

        Assert.assertNotNull(session.findChannelByName("bot"));
        session.disconnect();
    }


    @Test
    public void should_pass_if_bot_is_listening() throws IOException {

        session.addMessagePostedListener((event, session) -> {

            SlackChannel channel = session.findChannelByName("bot");

            if (!channel.getId().equals(event.getChannel().getId())) {
                return;
            }

            String messageContent = event.getMessageContent();
            if (!messageContent.contains("keyword")) {
                return;
            }

            session.sendMessage(event.getChannel(),"I read this !");
        });
        session.disconnect();
    }
}

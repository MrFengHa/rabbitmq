package workquene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/4 10:23
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0; i < 10; i++)  {
            channel.basicPublish("","work", null,(i+"hello work guene").getBytes(StandardCharsets.UTF_8));
        }

        RabbitMQUtils.closeConnectionChanel(channel,connection);

    }
}

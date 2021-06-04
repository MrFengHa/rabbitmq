package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/4 14:26
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String routingkey = "error";
        channel.basicPublish("logs_direct",routingkey,null,("这是direct模型发布的基于route_key：["+routingkey+"]发送的消息").getBytes(StandardCharsets.UTF_8));
        RabbitMQUtils.closeConnectionChanel(channel,connection);
    }
}

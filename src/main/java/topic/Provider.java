package topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/4 15:22
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topics","topic");
        //设置动态路由key
        String routekey = "user.save";
        //发布消息
        channel.basicPublish("topics",routekey,null,("这是路由中订阅动态模型,route key:["+routekey+"]").getBytes(StandardCharsets.UTF_8));
        RabbitMQUtils.closeConnectionChanel(channel,connection);
    }
}

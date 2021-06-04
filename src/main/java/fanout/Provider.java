package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/4 11:42
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //将通道声明指定的交换机
        //参数1： 交换机名称
        //参数2：交换机类型 fanout 广播类型
        channel.exchangeDeclare("logs","fanout");
        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes(StandardCharsets.UTF_8));

        RabbitMQUtils.closeConnectionChanel(channel,connection);

    }
}

package helloword;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/3 15:30
 */
public class Provider {

    /**
     * 生产消息
     */
    @Test
    public void testSendMessage() throws Exception{
        Connection connection = RabbitMQUtils.getConnection();


        //获取连接通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1：队列名称，如果队列不存在那么自动创建
        //参数2:用来定义队列特性是否持久化 true持久化队列，false不持久化
        //参数3：exclusive 是否独占队列 true是否独占队列  false不独占
        //参数4：autoDelete：是否在消费完成后自动删除队列 true自动删除 false不自动删除
        //参数5：额外参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1：交换机名称
        //参数2：队列名称
        //参数3：传递消息额外设置
        //参数4：消息的具体内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes(StandardCharsets.UTF_8));
        RabbitMQUtils.closeConnectionChanel(channel,connection);
    }
}

package helloword;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/3 15:56
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        //创建对象
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.queueDeclare("hello",false,false,false,null);
        //消费消息
        //参数1：消费那个队列的消息  队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override //最后一个参数：消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body)="+new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
    @Test
    public void test()throws Exception{

    }
}

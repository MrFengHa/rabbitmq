package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * @author 冯根源
 * @version 1.0
 * @date 2021/6/3 16:45
 */
public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("47.242.214.81");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("feng");
        connectionFactory.setPassword("feng");
    }
    /**
     * 定义提供连接对象的方法
     *
     * @return
     */
    public static Connection getConnection() {

        try {

            //创建连接对象
            Connection connection = connectionFactory.newConnection();

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭通道和关闭连接工具方法
     *
     * @param channel
     * @param connection
     */
    public static void closeConnectionChanel(Channel channel, Connection connection) {
        try {
            if (channel!=null){
                channel.close();
            }
            if (connection!=null){
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



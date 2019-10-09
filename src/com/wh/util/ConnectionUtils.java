package com.wh.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Because of you on 2019/9/17.
 */
public class ConnectionUtils {
    public static ConnectionFactory factory = new ConnectionFactory();

    public static Connection getConnection() throws IOException, TimeoutException {
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("user1");
        factory.setPassword("123456");
        return factory.newConnection();
    }
}

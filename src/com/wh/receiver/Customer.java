package com.wh.receiver;

import com.rabbitmq.client.*;
import com.wh.util.ConnectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Because of you on 2019/9/17.
 */
public class Customer {
    public static final  String pbxName ="pbx";
    public static final  String routingKey = "routingKey";
    public static void main(String[] args) throws IOException, TimeoutException {
        String quequeName = "queue1";
        //获取连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        Map<String,Object> arg = new HashMap<String,Object>();
        AMQP.Queue.DeclareOk ok = channel.queueDeclare(quequeName,true,false,false,arg);
        channel.queueBind(ok.getQueue(),pbxName,routingKey);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumerTag:" + consumerTag);
                System.out.println("envelope:" + envelope.toString());
                System.out.println("properties:" + properties.toString());
                System.out.println("消息内容:" + new String(body));
            }
        };
        channel.basicConsume(ok.getQueue(),true,"customer1",consumer);
    }
}

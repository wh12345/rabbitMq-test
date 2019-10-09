package com.wh.sender;

import com.rabbitmq.client.*;
import com.wh.util.ConnectionUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * Created by Because of you on 2019/9/17.
 */
public class Producter {
    public static final  String pbxName ="pbx";
    public static final  String routingKey = "routingKey";
    public static void main(String[] args) {
        try {
            //获取连接
            Connection connection = ConnectionUtils.getConnection();
            //获取通道
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(pbxName, "direct",true,false,false,new HashMap());
            AMQP.BasicProperties properties = new AMQP.BasicProperties()
                    .builder().deliveryMode(2)
                    .contentType("utf-8").contentEncoding("utf-8")
                    .headers(new HashMap()).build();
            String msg = "HelloWorld!";
            channel.basicPublish(pbxName,routingKey,false,properties,msg.getBytes());
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

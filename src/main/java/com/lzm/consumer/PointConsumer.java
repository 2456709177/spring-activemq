package com.lzm.consumer;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PointConsumer {

	public static void main(String[] args) throws JMSException, IOException {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//获取connection
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//获取queue
		Queue queue = session.createQueue("test01");
		//创建消费者
		MessageConsumer consumer=session.createConsumer(queue);
		//给消费者注册监听
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage textMessage=(TextMessage)message;
				try {
					System.out.println("提取的消息："+ textMessage.getText() );
				} catch (JMSException e) {					
					e.printStackTrace();
				}
			}
		});

		//8.等待键盘输入
		System.in.read();

		//9.关闭资源
		consumer.close();
		session.close();
		connection.close();
	}

}

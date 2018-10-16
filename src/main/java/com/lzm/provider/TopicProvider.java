package com.lzm.provider;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

public class TopicProvider {

	public static void main(String[] args) throws JMSException {
		// TODO Auto-generated method stub
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.25.128:61616");
		//获取connection
		Connection connection = connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//获取session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建topic
		Topic topic = session.createTopic("MyTopic");
		//消息提供者
		MessageProducer producer = session.createProducer(topic);
		//创建消息
		Message message = session.createTextMessage("测试消息....");
		//发送消息
		producer.send(message);
		producer.close();
		session.close();
		connection.close();
	}

}

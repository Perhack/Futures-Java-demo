package com.perhack.showingsword.rabbitmq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class Main {

    public Main() throws Exception
    {

        // ���������ߣ�����Ϣ�����ߣ��������߳�
        QueueConsumer consumer = new QueueConsumer("localhost");
        Thread consumerThread = new Thread(consumer);
        consumerThread.start();

        // ���������ߣ�����Ϣ������
//        Producer producer = new Producer("queue","localhost");
//
//        // ѭ��������Ϣ
//        for (int i = 0; i < 20; i++){
//            HashMap message = new HashMap();
//            message.put("message number", i);
//            producer.sendMessage(message);
//            System.out.println("Message Number " + i + " sent.");
//        }
    }

    /**
     * @param args
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception{
        new Main();
    }
}

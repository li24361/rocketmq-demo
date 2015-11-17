package org.felix.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

import java.text.SimpleDateFormat;

/**
 * Created by 01401544 on 2015/11/16.
 */
public class ProducerSingleton {


    private static class SingletonHolder {
        private static final DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");

    }


    private ProducerSingleton (){}

    public static final DefaultMQProducer getInstance() {
        SingletonHolder.producer.setNamesrvAddr("10.135.111.22:9876");
        SingletonHolder.producer.setInstanceName("Producer");
        return SingletonHolder.producer;
    }
}

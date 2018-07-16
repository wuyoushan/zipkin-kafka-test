package cqut.wys.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * --------神兽出没--------/
 *   ┌─┐     ┌─┐
 * ┌─┘─┴─────┘─┴─┐
 * │      ─      │
 * │  ┬─┘   └─┬  │
 * │             │
 * │      ┴      │  Code is far away from bug with the animal protecting
 * └───┐      ┌──┘  神兽保佑,代码无bug
 *     │      │
 *     │      └──────┐
 *     │             ├┐
 *     │             ┌┘
 *     └┐ ┐ ┌───┬─┐ ┌┘
 *      │ ┤ ┤   │ ┤ ┤
 *      └─┴─┘   └─┴─┘
 * --------感觉萌萌哒--------/
 *
 * kafka发送消息
 * @author wuyoushan
 * @date 2018/7/5
 */
@Component
public class KafkaProducer {

    @Autowired
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, byte[] message) {
        this.kafkaTemplate.send(topic, message);
        System.out.println("Send message=" + new String(message));
    }
}

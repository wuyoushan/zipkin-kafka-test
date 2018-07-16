package cqut.wys.biz;

import org.springframework.kafka.annotation.KafkaListener;

/**
 * --------神兽出没--------/
 * ┌─┐     ┌─┐
 * ┌─┘─┴─────┘─┴─┐
 * │      ─      │
 * │  ┬─┘   └─┬  │
 * │             │
 * │      ┴      │  Code is far away from bug with the animal protecting
 * └───┐      ┌──┘  神兽保佑,代码无bug
 * │      │
 * │      └──────┐
 * │             ├┐
 * │             ┌┘
 * └┐ ┐ ┌───┬─┐ ┌┘
 * │ ┤ ┤   │ ┤ ┤
 * └─┴─┘   └─┴─┘
 * --------感觉萌萌哒--------/
 *
 * @author wuyoushan
 * @date 2018/7/12
 */
public class KafkaConsumer {

        @KafkaListener(topics = "zipkin")
        public void processMessage(String message) {
            System.out.println("Received message [" + message + "]");
        }
}

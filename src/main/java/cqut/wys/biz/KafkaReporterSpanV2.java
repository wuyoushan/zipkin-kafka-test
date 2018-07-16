package cqut.wys.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

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
 * zipkin使用kafka传输数据  v2数据模型
 *
 * @author wuyoushan
 * @date 2018/7/6
 */
@Component
public class KafkaReporterSpanV2 implements Reporter<Span> {

    @Value("${zipkin.topic:zipkin}")
    private String zipkinTopic;

    private KafkaProducer kafkaProducer;

    @Autowired
    public void setKafkaProducer(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void report(Span span) {
        System.out.println("span to String = " + span.toString());
        String mess = "["+span.toString()+"]";
        kafkaProducer.send(zipkinTopic, mess.getBytes());
    }
}

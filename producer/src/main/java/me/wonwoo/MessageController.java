package me.wonwoo;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MessageChannel consumer;

    public MessageController(ProducerChannels channels) {
        this.consumer = channels.consumer();
    }

    @GetMapping("/{name}")
    public void publish(@PathVariable String name) {
        String hello = "hello " + name + "!";
        Message<String> message = MessageBuilder.withPayload(hello).build();
        consumer.send(message);
    }
}

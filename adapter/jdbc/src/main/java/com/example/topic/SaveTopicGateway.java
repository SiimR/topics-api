package com.example.topic;

import com.example.domain.entity.Topic;
import com.example.domain.port.SaveTopicPort;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.app.tables.Topic.TOPIC;

@Component
@RequiredArgsConstructor
class SaveTopicGateway implements SaveTopicPort {
    private final DSLContext dsl;

    @Override
    public UUID save(Topic topic) {
        var newTopic = dsl.newRecord(TOPIC);
        newTopic.set(TOPIC.BODY, topic.body());
        newTopic.set(TOPIC.TITLE, topic.title());
        newTopic.store();
        return newTopic.getId();
    }
}

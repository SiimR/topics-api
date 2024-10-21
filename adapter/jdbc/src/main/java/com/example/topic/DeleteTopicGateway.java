package com.example.topic;

import com.example.domain.port.DeleteTopicPort;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.example.app.Tables.TOPIC;

@Component
@RequiredArgsConstructor
class DeleteTopicGateway implements DeleteTopicPort {
    private final DSLContext dsl;

    public void delete(UUID topicId) {
        dsl.deleteFrom(TOPIC)
                .where(TOPIC.ID.eq(topicId))
                .execute();
    }
}

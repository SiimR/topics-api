package com.example.topic;

import com.example.domain.entity.Topic;
import com.example.domain.port.FetchTopicPort;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.example.app.tables.Topic.TOPIC;

@Component
@RequiredArgsConstructor
class FetchTopicGateway implements FetchTopicPort {
    private final DSLContext dsl;

    @Override
    public Optional<Topic> fetch(UUID topicId) {
        return dsl.select()
                .from(TOPIC)
                .where(TOPIC.ID.eq(topicId))
                .fetchOptionalInto(Topic.class);
    }
}

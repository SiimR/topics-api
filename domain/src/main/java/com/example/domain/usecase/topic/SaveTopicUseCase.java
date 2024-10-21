package com.example.domain.usecase.topic;

import com.example.domain.entity.Topic;

import java.util.UUID;

public interface SaveTopicUseCase {
    Response execute(Request request);

    record Request(Topic topic) {}

    record Response(UUID topicUuid) {}
}

package com.example.domain.usecase.topic;

import com.example.domain.entity.Topic;

import java.util.UUID;

public interface FetchTopicUseCase {
    Response execute(Request request);

    record Request(UUID topicId) {}

    record Response(Topic topic) {}
}

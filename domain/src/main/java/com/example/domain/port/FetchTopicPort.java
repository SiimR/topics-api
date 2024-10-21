package com.example.domain.port;

import com.example.domain.entity.Topic;

import java.util.Optional;
import java.util.UUID;

public interface FetchTopicPort {
    Optional<Topic> fetch(UUID topicId);
}

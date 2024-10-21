package com.example.domain.port;

import java.util.UUID;

public interface DeleteTopicPort {
    void delete(UUID topicId);
}

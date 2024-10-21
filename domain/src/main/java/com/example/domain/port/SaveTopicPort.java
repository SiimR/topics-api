package com.example.domain.port;

import com.example.domain.entity.Topic;

import java.util.UUID;

public interface SaveTopicPort {
    UUID save(Topic topic);
}

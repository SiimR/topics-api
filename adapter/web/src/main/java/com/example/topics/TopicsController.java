package com.example.topics;

import com.example.domain.entity.Topic;
import com.example.domain.port.DeleteTopicPort;
import com.example.domain.usecase.topic.FetchTopicUseCase;
import com.example.domain.usecase.topic.SaveTopicUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("topic")
class TopicsController {

    private final FetchTopicUseCase fetchTopicUseCase;
    private final SaveTopicUseCase saveTopicUseCase;
    private final DeleteTopicPort deleteTopicPort;

    @GetMapping("/{topicId}")
    public ResponseEntity<Topic> getTopic(@PathVariable("topicId") UUID topicId) {
        var response = fetchTopicUseCase.execute(new FetchTopicUseCase.Request(topicId));
        return ResponseEntity.ok(response.topic());
    }

    @PostMapping
    public ResponseEntity<UUID> saveTopic(@RequestBody Topic topic) {
        var response = saveTopicUseCase.execute(new SaveTopicUseCase.Request(topic));
        return ResponseEntity.ok(response.topicUuid());
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("topicId") UUID topicId) {
        deleteTopicPort.delete(topicId);
        return ResponseEntity.ok().build();
    }
}

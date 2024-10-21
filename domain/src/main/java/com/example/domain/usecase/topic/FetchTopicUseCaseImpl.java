package com.example.domain.usecase.topic;

import com.example.domain.port.FetchTopicPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FetchTopicUseCaseImpl implements FetchTopicUseCase {
    private final FetchTopicPort fetchTopicPort;

    @Override
    public Response execute(Request request) {
        var topicOptional = fetchTopicPort.fetch(request.topicId());
        return new Response(topicOptional.orElse(null));
    }
}

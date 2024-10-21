package com.example.domain.usecase.topic;

import com.example.domain.port.SaveTopicPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SaveTopicUseCaseImpl implements SaveTopicUseCase {
    private final SaveTopicPort saveTopicPort;

    @Override
    public Response execute(Request request) {
        var uuid = saveTopicPort.save(request.topic());
        return new Response(uuid);
    }
}

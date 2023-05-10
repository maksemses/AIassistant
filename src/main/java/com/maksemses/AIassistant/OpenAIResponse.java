package com.maksemses.AIassistant;


import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

import java.time.Duration;
import java.util.List;

public class OpenAIResponse {
    final String API_KEY = "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";
    static OpenAiService service;

    public OpenAIResponse(){
        service = new OpenAiService(API_KEY, Duration.ofMinutes(2));
    }
    public String getAnswer(String question){
        CompletionRequest completionRequest;
        completionRequest = CompletionRequest.builder()
                .prompt(question)
                .maxTokens(2000)
                .echo(true)
                .temperature(0.5)
                .topP(1.0)
                .frequencyPenalty(0.0)
                .presencePenalty(0.0)
                .model("text-davinci-003")
                .build();
        List<CompletionChoice> answerList = service.createCompletion(completionRequest).getChoices();
        CompletionChoice answerChoice = answerList.get(0);
        return answerChoice.getText();
    }
}
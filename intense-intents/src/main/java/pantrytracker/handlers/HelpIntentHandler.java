package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "You can check and update your inventory of items. For example, add five bags of flour. You can also order more items.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("IntenseIntents", speechText)
                .withReprompt(speechText)
                .build();
    }
}
package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import pantrytracker.util.Handlers;

import static com.amazon.ask.request.Predicates.intentName;

public class CancelandStopIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	Handlers.handleQuit(input);
    	
        String speechText = "Goodbye";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("IntenseIntents", speechText)
                .build();
    }
}
package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import pantrytracker.model.Attributes;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AddIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.START_STATE)));

    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        IntentRequest intentRequest = (IntentRequest)input.getRequestEnvelope().getRequest();
        int amount = 1;
        if(intentRequest.getIntent().getSlots().get("amount").getValue() != null) {
            amount = Integer.parseInt(intentRequest.getIntent().getSlots().get("amount").getValue());
        }
        String food = intentRequest.getIntent().getSlots().get("food").getValue();
        //if(item in inventory)
        //increase by amount
        //else
        //add item with quantity amount
        return input.getResponseBuilder().withSpeech("Done. You now have " + amount + " " + food + " in your pantry.").withShouldEndSession(true).build();
    }
}

package pantrytracker.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import pantrytracker.model.Attributes;
import pantrytracker.util.Handlers;

public class NoIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.NoIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.ORDER_MORE_STATE)));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		if(sessionAttributes.get(Attributes.STATE_KEY) == Attributes.ORDER_MORE_STATE) {
			sessionAttributes.put(Attributes.STATE_KEY, Attributes.SHOPPING_LIST_STATE);
			return input.getResponseBuilder().withSpeech("Would you like to add it to your shopping list?").withShouldEndSession(false).build();
		} else {
			return Handlers.handleQuit(input);
		}
	}

}

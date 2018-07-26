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

public class YesIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.YesIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.ORDER_MORE_STATE)));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		sessionAttributes.put(Attributes.STATE_KEY, Attributes.HOW_MANY_STATE);
		return null;
	}

}

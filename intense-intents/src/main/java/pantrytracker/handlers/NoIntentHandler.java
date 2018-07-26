package pantrytracker.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

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
		return Handlers.handleQuit(input);
	}

}

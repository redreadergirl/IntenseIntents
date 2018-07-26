package pantrytracker.handlers;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
import pantrytracker.util.DynamoUtil;
import pantrytracker.util.Handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class QueryIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("QueryIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.START_STATE).or(Handlers.noState())));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String userId = input.getRequestEnvelope().getSession().getUser().getUserId();
		
		Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
		
		DynamoUtil ddb = new DynamoUtil("", null); //TODO replace with the correct stuff
		
		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		String item = intentRequest.getIntent().getSlots().get("food").getValue();
		
		int countItem = ddb.getQuantity(userId, item);
		
		String message = "You have " + countItem + " " + item + " in your pantry. ";
		
		//TODO figure out if we want to quit after this or go back to the menu
		
		return AlexaOutput.createDefault(message).getOutput(input);
		
	}

}

package pantrytracker.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
import pantrytracker.model.Constants;
import pantrytracker.util.Handlers;

public class OrderIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("OrderIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.START_STATE).or(Handlers.noState())));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
//		sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
		
		IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
		Map<String,Slot> slots = intentRequest.getIntent().getSlots();
		String item = slots.get("food").getValue();
		String amount = slots.get("amount").getValue();
		
		if (amount==null) { //no amount given, so need to ask
			sessionAttributes.put(Attributes.STATE_KEY, Attributes.HOW_MANY_STATE);
			sessionAttributes.put(Attributes.ITEM_KEY, item);
			return AlexaOutput.createDefault(Constants.HOW_MANY_MESSAGE).getOutput(input);
		}
		//amount given
		
		//TODO order amount of stuff
		
		//TODO figure out if we want to quit after this or go back to the menu
		
		return AlexaOutput.createDefault(Constants.PLACEHOLDER_MESSAGE).getOutput(input);
	}

}

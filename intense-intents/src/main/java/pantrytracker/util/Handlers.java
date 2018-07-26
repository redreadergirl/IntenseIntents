package pantrytracker.util;

import java.util.HashSet;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
import pantrytracker.model.Constants;

public class Handlers {
	
	public static Predicate<HandlerInput> noState() {
		return input -> !(input.getAttributesManager().getSessionAttributes().containsKey(Attributes.STATE_KEY));
	}

	public static Optional<Response> handleQuit(HandlerInput input) {
		Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		Set<String> keys = new HashSet<String>(sessionAttributes.keySet());
		for (String key : keys) {
			sessionAttributes.remove(key);
		}
		return AlexaOutput.createDefault(Constants.EXIT_MESSAGE).getOutput(input, true);
	}
	
}

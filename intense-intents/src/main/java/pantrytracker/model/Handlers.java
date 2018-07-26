package pantrytracker.model;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class Handlers {

	public Optional<Response> handleQuit(HandlerInput input) {
		Map<String,Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
		Set<String> keys = new HashSet<String>(sessionAttributes.keySet());
		for (String key : keys) {
			sessionAttributes.remove(key);
		}
		return AlexaOutput.createDefault(Constants.EXIT_MESSAGE).getOutput(input, true);
	}
	
}

package pantrytracker.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Constants;

public class DefaultIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(Predicates.requestType(IntentRequest.class));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return AlexaOutput.createDefault(Constants.IDK_MESSAGE).getOutput(input);
	}

}

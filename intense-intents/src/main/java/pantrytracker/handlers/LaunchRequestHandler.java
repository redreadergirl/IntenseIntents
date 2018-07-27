package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
import pantrytracker.model.Constants;

import java.util.Map;
import java.util.Optional;
 
/**
 * Called when the user launches the skill
 */
public class LaunchRequestHandler implements RequestHandler {
 
     @Override
     public boolean canHandle(HandlerInput input) {
         return input.matches(Predicates.requestType(LaunchRequest.class));
     }
 
     @Override
     public Optional<Response> handle(HandlerInput input) {
    	 Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
    	 sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
    	 
<<<<<<< HEAD
    	 return AlexaOutput.createDefault(Constants.START_MESSAGE).getOutput(input);
=======
    	 return input.getResponseBuilder().withSpeech(Constants.START_MESSAGE).withShouldEndSession(false).build();
>>>>>>> 8a79f10... remove shopping list, hopefully fix closing issue
     }
 
}
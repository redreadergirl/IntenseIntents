package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import pantrytracker.model.Constants;

import java.util.Optional;
 
/**
 * @author mvvadari
 * Called when the user launches the skill
 */
public class LaunchRequestHandler implements RequestHandler {
 
     @Override
     public boolean canHandle(HandlerInput input) {
         return input.matches(Predicates.requestType(LaunchRequest.class));
     }
 
     @Override
     public Optional<Response> handle(HandlerInput input) {
    	 return input.getResponseBuilder().withSpeech(Constants.START_MESSAGE).build();
     }
 
}
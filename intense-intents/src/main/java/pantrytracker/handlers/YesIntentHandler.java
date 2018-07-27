package pantrytracker.handlers;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import com.amazon.ask.response.ResponseBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
import pantrytracker.model.Constants;
import pantrytracker.util.Handlers;

public class YesIntentHandler implements RequestHandler {

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(intentName("AMAZON.YesIntent").and(sessionAttribute(Attributes.STATE_KEY, Attributes.ORDER_MORE_STATE)));
	}

	@Override
	public Optional<Response> handle(HandlerInput input) {
		Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
            String food = (String) sessionAttributes.get(Attributes.ITEM_KEY);
            return input.getResponseBuilder().withSpeech("If you are sure you would like to purchase this item please say, Alexa buy " + food).withShouldEndSession(true).build();
	}

}

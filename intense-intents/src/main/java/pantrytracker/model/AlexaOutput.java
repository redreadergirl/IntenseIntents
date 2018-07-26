package pantrytracker.model;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

public class AlexaOutput {

	public static AlexaOutput createDefault(String message) {
		return new AlexaOutput(message, message, Constants.CARD_SKILL_NAME, message);
	}
	
	
	private final String speech;
	private final String reprompt;
	private final String cardTitle;
	private final String cardContent;
	
	public AlexaOutput(String speech, String reprompt, String cardTitle, String cardContent) {
		this.speech = speech;
		this.reprompt = reprompt;
		this.cardTitle = cardTitle;
		this.cardContent = cardContent;
	}
	
	public Optional<Response> getOutput(HandlerInput input) {
		return getOutput(input, false);
	}
	
	public Optional<Response> getOutput(HandlerInput input, boolean shouldEndSession) {
		return input.getResponseBuilder()
				.withSpeech(speech)
				.withReprompt(reprompt)
				.withSimpleCard(cardTitle, cardContent)
				.withShouldEndSession(shouldEndSession)
				.build();
	}
	
}

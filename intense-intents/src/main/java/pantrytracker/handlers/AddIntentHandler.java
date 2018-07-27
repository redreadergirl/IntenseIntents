package pantrytracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import pantrytracker.model.AlexaOutput;
import pantrytracker.model.Attributes;
<<<<<<< HEAD
import pantrytracker.util.Handlers;
=======
import pantrytracker.util.DynamoUtil;
>>>>>>> f6b5e57... add methods to addIntent

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

public class AddIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AddIntent").and(Handlers.noState().or(sessionAttribute(Attributes.STATE_KEY, Attributes.START_STATE))));

    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	System.out.println("Starting add intent handler");
        String userId = input.getRequestEnvelope().getSession().getUser().getUserId();

        IntentRequest intentRequest = (IntentRequest)input.getRequestEnvelope().getRequest();
        int amount = 1;
        if(intentRequest.getIntent().getSlots().get("amount").getValue() != null) {
            amount = Integer.parseInt(intentRequest.getIntent().getSlots().get("amount").getValue());
        }
        String food = intentRequest.getIntent().getSlots().get("food").getValue();
        System.out.println("Slot values: food is " + food + " and amount is " + amount);
        DynamoUtil du = new DynamoUtil();
        System.out.println("Created DynamoUtil");
        du.changeInventory(userId, food, amount);
        System.out.println("Inventory changed");
        amount = du.getQuantity(userId, food);
        
        String speech = "Done. You now have " + amount + " " + food + " in your pantry. ";
        
        return AlexaOutput.createDefault(speech).getOutput(input);
    }
}

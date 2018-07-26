package pantrytracker;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;

import pantrytracker.handlers.*;

import com.amazon.ask.SkillStreamHandler;
  
public class PantryTrackerStreamHandler extends SkillStreamHandler {
  
    private static Skill getSkill() {
        return Skills.standard()
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                .addRequestHandlers(new LaunchRequestHandler())
                .addRequestHandlers(new AddIntentHandler())
=======
                .addRequestHandlers(new LaunchRequestHandler(), new QueryIntentHandler())
>>>>>>> 50fddbc... Created QueryIntentHandler
=======
                .addRequestHandlers(new LaunchRequestHandler(), new QueryIntentHandler(), new AddIntentHandler(), new RemoveIntentHandler(), new OrderIntentHandler())
>>>>>>> cbde3b9... Wrote most of the OrderIntentHandler, some reorganization
=======
                .addRequestHandlers(new LaunchRequestHandler(), new QueryIntentHandler(), new AddIntentHandler(), new RemoveIntentHandler(), new OrderIntentHandler(), 
                					new YesIntentHandler(), new NoIntentHandler())
>>>>>>> 79d8b4b... Wrote yes and no handlers
                .build();
    }
  
    /**
     * Create new LSMStreamHandler object, for Alexa to call
     */
    public PantryTrackerStreamHandler() {
        super(getSkill());
    }

}

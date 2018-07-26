package pantrytracker;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;

import pantrytracker.handlers.*;

import com.amazon.ask.SkillStreamHandler;
  
public class PantryTrackerStreamHandler extends SkillStreamHandler {
  
    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new LaunchRequestHandler())
                .build();
    }
  
    /**
     * Create new LSMStreamHandler object, for Alexa to call
     */
    public PantryTrackerStreamHandler() {
        super(getSkill());
    }

}

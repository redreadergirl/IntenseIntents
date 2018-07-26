package pantrytracker.model;

@SuppressWarnings("javadoc")
public class Constants {
	
	public static final String CARD_SKILL_NAME = "Pantry Tracker";
	
	public static final String START_MESSAGE = "BLEH";
<<<<<<< HEAD
	public static final String EXIT_MESSAGE = "Thank you for using " + CARD_SKILL_NAME + ". Goodbye. ";
<<<<<<< HEAD
=======

	public static final String PARTITION_KEY = "userId";
=======
	
	public static final String HOW_MANY_MESSAGE = "How many would you like to order? ";
	public static final String ORDER_SUCCESSFUL_MESSAGE = "Your order has been placed! ";
	
	public static final String PLACEHOLDER_MESSAGE = "I'm sorry, this function has not yet been implemented. Please come back later. ";
	
>>>>>>> cbde3b9... Wrote most of the OrderIntentHandler, some reorganization
	public static final String AWS_REGION = "us-west-2";
	public static final String DDB_TABLE_NAME = "IntenseIntents";
    public static final String DDB_ARN = "arn:aws:dynamodb:us-west-2:081960220439:table/IntenseIntents";
<<<<<<< HEAD
>>>>>>> 82ca079... added info for DDB table
=======
    public static final String INVENTORY_MAP = "Inventory";
>>>>>>> 9359a69... added ddb map constant
}

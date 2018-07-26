<<<<<<< HEAD
// Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
package pantrytracker.util;
=======
package pantrytracker.util
>>>>>>> 8003843... Removed Logger

import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;

<<<<<<< HEAD
public class DynamoUtil {
<<<<<<< HEAD
//    private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDBStatClient.class);
=======
>>>>>>> 8003843... Removed Logger
    private static final String TABLE_NAME = "IntenseIntents";
=======
import java.util.HashMap;
import pantrytracker.model.Constants;
>>>>>>> 428a3a9... Fixed DDB functionality

public class DynamoUtil {
    private final DynamoDB dynamoDB;
    private final Table table;

    public DynamoUtil() {
        // Uncomment for remote connection
        //this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2).build());

<<<<<<< HEAD
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration
                (new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build());
        
        this.table = dynamoDB.getTable(Constants.DDB_TABLE_NAME);
=======
    public DynamoUtil(final String tableName, final AmazonDynamoDB client) {
        this.dynamoDB = new DynamoDB(client);
>>>>>>> cbde3b9... Wrote most of the OrderIntentHandler, some reorganization
    }
    
    // See if an item is in stock
    public Boolean isInStock(final String userId, final String itemName) {
    	return getQuantity(userId, itemName) > 0;
    }

    // Should return the new ingredient count
    public int addInventory(final String userId, final String itemName, final int num) {
        // Invalid args.
        if (userId.isEmpty() || itemName.isEmpty() || num < 0) return 0;

        final GetItemSpec spec = new GetItemSpec().withPrimaryKey(Constants.PARTITION_KEY, userId);
        final Item outcome = dynamoTable.getItem(spec);

        // User did not exist
        if (outcome == null) {
            createInventoryRow(userId);
            HashMap<String, Integer> items = new Map<String, Integer>;
            items.put(itemName) = num;

            final Item record = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_NAME, items);
            dynamoTable.putItem(record);

        } else {
            HashMap<String, Integer> items = (HashMap<String, Integer>) outcome.getMap(Constants.INVENTORY_NAME);

            // Check to see if the product is already in the list. If so, add to the value.
            if (items.containsKey(itemName)) {
                items.put(itemName) = items.get(itemName) + num;
            }
            else {
                items.put(itemName) = num;
            }

            final Item record = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_NAME, items);
            dynamoTable.putItem(record);
        }
    }
   
   // Grabs itemName quantity
    public int getQuantity(final String userId, String itemName) {
        // Invalid args
        if (itemName.isEmpty() || userID.isEmpty()) return 0;

    	final GetItemSpec spec = new GetItemSpec().withPrimaryKey(Constants.PARTITION_KEY, userId);
        final Item outcome = dynamoTable.getItem(spec);


        // If user was not in our DB
        if (outcome == null) {
            createInventoryRow(userId);
            return 0;
        }
        else {
            HashMap<String, Integer> items = outcome.getMap(Constants.INVENTORY_NAME);

            // Check if user has product
            if (items.containsKey(itemName)) {
                return items.get(itemName);
            }
            else return 0;
        }
    } 

    private Boolean createInventoryRow(final String userId) {
        try {
            Map<String, Integer> inventory = new HashMap<String, Integer>();
            Item item = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_NAME, inventory);
            dynamoTable.putItem(record);
            return true;
        }
        catch (AmazonServiceException ase) {
            return false;
        }
    }
}

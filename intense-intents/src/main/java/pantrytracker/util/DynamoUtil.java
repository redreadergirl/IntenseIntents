package pantrytracker.util;

import java.math.BigDecimal;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;

import java.util.HashMap;
import java.util.Map;

import pantrytracker.model.Constants;

public class DynamoUtil {
    private final DynamoDB dynamoDB;
    private final Table dynamoTable;

    public DynamoUtil() {
        // Uncomment for remote connection
        //this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_WEST_2).build());
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration
                (new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2")).build());
        
        this.dynamoTable = dynamoDB.getTable(Constants.DDB_TABLE_NAME);
    }
    
    // See if an item is in stock
    public Boolean isInStock(final String userId, final String itemName) {
    	return getQuantity(userId, itemName) > 0;
    }

    // Should return the new ingredient count
    public int changeInventory(final String userId, final String itemName, final int num) {
        // Invalid args.
        if (userId.isEmpty() || itemName.isEmpty()) return 0;

        int newCount = num;

        final GetItemSpec spec = new GetItemSpec().withPrimaryKey(Constants.PARTITION_KEY, userId);
        final Item outcome = dynamoTable.getItem(spec);

        // User did not exist
        if (outcome == null) {
            createInventoryRow(userId);
            Map<String, Integer> items = new HashMap<String, Integer>();
            items.put(itemName, (num < 0) ? 0 : num);

            final Item record = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_MAP, items);
            dynamoTable.putItem(record);
        } else {
            Map<String, BigDecimal> items = outcome.getMap(Constants.INVENTORY_MAP);

            // Check to see if the product is already in the list. If so, add to the value.

            if (items.containsKey(itemName)) {
                newCount += (items.get(itemName)).intValue();
                items.put(itemName, (newCount < 0) ? (new BigDecimal(0)) : (new BigDecimal(newCount)));
            }
            else {
                items.put(itemName, items.put(itemName,
                        (newCount < 0) ? (new BigDecimal(0)) : (new BigDecimal(newCount))));
            }

            final Item record = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_MAP, items);
            dynamoTable.putItem(record);
        }

        return (newCount < 0) ? 0 : newCount;
    }
   
   // Grabs itemName quantity
    public int getQuantity(final String userId, String itemName) {
        // Invalid args
        if (itemName.isEmpty() || userId.isEmpty()) return 0;

    	final GetItemSpec spec = new GetItemSpec().withPrimaryKey(Constants.PARTITION_KEY, userId);
        final Item outcome = dynamoTable.getItem(spec);


        // If user was not in our DB
        if (outcome == null) {
            createInventoryRow(userId);
            return 0;
        }
        else {
            Map<String, BigDecimal> items = outcome.getMap(Constants.INVENTORY_MAP);

            // Check if user has product
            if (items.containsKey(itemName)) {
                return ((BigDecimal) items.get(itemName)).intValue();
            }
            else return 0;
        }
    } 

    private Boolean createInventoryRow(final String userId) {
        try {
            Map<String, Integer> inventory = new HashMap<String, Integer>();
            Item item = new Item().withPrimaryKey(Constants.PARTITION_KEY, userId).withMap(Constants.INVENTORY_MAP, inventory);
            dynamoTable.putItem(item);
            return true;
        }
        catch (AmazonServiceException ase) {
            return false;
        }
    }
}

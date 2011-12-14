#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UniqueIdProcessor implements ItemProcessor<String, String> {

    private static final Logger logger = LoggerFactory.getLogger(UniqueIdWriter.class);

    @Override
    public String process(String item) throws Exception {
        logIncoming(item);

        String processedItem = processed(item);

        logOutgoing(processedItem);
        return processedItem;
    }

    private String processed(String item) {
        return "P" + item;
    }

    private void logIncoming(String item) {
        logger.info("Processing incoming item: {}", item);
    }

    private void logOutgoing(String item) {
        logger.info("Processed outgoing item: {}", item);
    }
}

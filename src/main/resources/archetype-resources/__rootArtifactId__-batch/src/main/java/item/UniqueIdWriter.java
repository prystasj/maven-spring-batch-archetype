#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UniqueIdWriter implements ItemWriter<String> {

    private static final Logger logger = LoggerFactory.getLogger(UniqueIdWriter.class);

    @Override
    public void write(List<? extends String> items) throws Exception {
        for(String item : items) {
            logWriteOf(item);
        }
    }

    private void logWriteOf(String item) {
        logger.info("Writing to log: {}", item);
    }

}

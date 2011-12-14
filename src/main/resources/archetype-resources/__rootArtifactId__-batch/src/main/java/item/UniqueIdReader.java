#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.item;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import java.util.UUID;

public class UniqueIdReader implements ItemReader, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(UniqueIdReader.class);

    private long readsToProcess;
    private long readsCompleted = 0;

    public String read() throws Exception {

        String uniqueId = null;

        if (readsCompleted < readsToProcess) {
            uniqueId = readUniqueId();
            readsCompleted++;
            logReadFor(uniqueId, readsCompleted);
        }

        return uniqueId;
    }

    private String readUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(readsToProcess);
        log(readsToProcess);
    }

    @Required
    public void setReadsToProcess(long readsToProcess) {
        this.readsToProcess = readsToProcess;
    }

    private void logReadFor(String uniqueId, long readNumber) {
        logger.info("Read ${symbol_pound}{}: {}", readNumber, uniqueId);
    }

    private void log(long readsToProcess) {
        logger.info("Performing {} reads", readsToProcess);
    }

}

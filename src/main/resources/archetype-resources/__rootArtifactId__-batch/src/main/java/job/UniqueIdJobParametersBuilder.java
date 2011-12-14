#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.Date;

public class UniqueIdJobParametersBuilder {

    private JobParametersBuilder jobParametersBuilder;

    public JobParameters buildJobParametersFor(long reads) {
        jobParametersBuilder.addLong("timestamp", timestamp());
        jobParametersBuilder.addLong("reads", reads);
        return jobParametersBuilder.toJobParameters();
    }

    private long timestamp() {
        return new Date().getTime();
    }

    public void setJobParametersBuilder(JobParametersBuilder jobParametersBuilder) {
        this.jobParametersBuilder = jobParametersBuilder;
    }

}

#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;

import java.util.List;

public class DefaultUniqueIdJobLauncher implements UniqueIdJobLauncher {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUniqueIdJobLauncher.class);

    private Job uniqueIdJob;
    private SimpleJobLauncher jobLauncher;
    private UniqueIdJobParametersBuilder jobParametersBuilder;

    @Override
    public String launchJob(long readsToProcess) throws IllegalStateException {

        logJobStart(readsToProcess);

        JobParameters jobParameters = jobParametersFor(readsToProcess);
        JobExecution jobExecution;

        try {
            jobExecution = jobLauncher.run(uniqueIdJob, jobParameters);
        }
        catch (Throwable t) {
            logException(t);
            throw new IllegalStateException("Job failed", t);
        }
        
        ExitStatus exitStatus = jobExecution.getExitStatus();

        if (isFailed(exitStatus)) {
            throw new IllegalStateException(exitStatus.getExitDescription(), exceptionFrom(jobExecution));
        }

        String result = exitStatus.getExitCode();
        logJobEnd(result);

        return result;
    }

    private JobParameters jobParametersFor(long readsToProcess) {
        return jobParametersBuilder.buildJobParametersFor(readsToProcess);
    }

    private boolean isFailed(ExitStatus exitStatus) {
        return exitStatus.equals(ExitStatus.FAILED);
    }

    private Throwable exceptionFrom(JobExecution jobExecution) {
        List<Throwable> exceptions = jobExecution.getAllFailureExceptions();
        Throwable firstException = null;

        if (exceptions.size() > 0) {
            firstException = exceptions.get(0);
        }

        return firstException;
    }

    private void logJobStart(Long reads) {
        logger.info("Starting job, reads: {}", reads.toString());
    }

    private void logJobEnd(String result) {
        logger.info("Job completed, status: {}", result);
    }

    private void logException(Throwable exception) {
        logger.warn("Exception: {}", exception.getMessage());
    }

    public void setJobLauncher(SimpleJobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public void setJobParametersBuilder(UniqueIdJobParametersBuilder jobParametersBuilder) {
        this.jobParametersBuilder = jobParametersBuilder;
    }

    public void setUniqueIdJob(Job uniqueIdJob) {
        this.uniqueIdJob = uniqueIdJob;
    }

}

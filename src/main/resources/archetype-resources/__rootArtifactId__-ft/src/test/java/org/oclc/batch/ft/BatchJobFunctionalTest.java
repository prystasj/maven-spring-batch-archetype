#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.it;

/*
 * Copyright (c) 2011, OCLC Online Computer Library Center, Inc.
 *
 * OCLC proprietary information: the enclosed materials contain proprietary information of 
 * OCLC Online Computer Library Center, Inc. and shall not be disclosed in whole or in any part to any
 * third party or used by any person for any purpose, without written consent of OCLC, Inc.
 * Duplication of any portion of these materials shall include his notice.
 *
 * User: prystasj
 * Date: 10/12/11
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/simple-job-launcher-context.xml", "/jobs/testJob.xml", "/job-runner-context.xml"})
public class BatchJobFunctionalTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void testLaunchJob() throws Exception {
        jobLauncherTestUtils.launchJob();
    }

}

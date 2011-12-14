#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.job;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface UniqueIdJobLauncher {

    @WebResult(name = "reads")
    String launchJob(@WebParam(name = "reads") long readsToProcess) throws IllegalStateException;

}

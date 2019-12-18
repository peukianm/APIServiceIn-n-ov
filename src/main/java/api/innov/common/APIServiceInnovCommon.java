package api.innov.common;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

public class APIServiceInnovCommon {
	
	public static void logServiceError(Exception ex, Logger logger) {
        try {
            logger.error("-----------AN ERROR HAPPENED !!!! -------------------- : " + ex.toString());
//            if (sessionBean.getUsers() != null) {
//                logger.error("User=" + sessionBean.getUsers().getUsername());
//            }
            logger.error("Cause=" + ex.getCause());
            logger.error("Class=" + ex.getClass());
            logger.error("Message=" + ex.getLocalizedMessage());
            logger.error(ex, ex);
            logger.error("--------------------- END OF ERROR --------------------------------------------------------\n\n");

//            ErrorBean errorBean = (ErrorBean) FacesUtils.getManagedBean("errorBean");
//            errorBean.reset();
//            errorBean.setErrorMSG(MessageBundleLoader.getMessage(sessionBean.getErrorMsgKey()));
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

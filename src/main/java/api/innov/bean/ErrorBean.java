package api.innov.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;


import javax.inject.Named;

@Named(value = "errorBean")
@RequestScoped
public class ErrorBean implements Serializable {

    String errorMSG = null;
    

    @PostConstruct
    public void init(){
        //errorMSG = MessageBundleLoader.getMessage("errMsg_GeneralError");                                        
    }
    
    @PreDestroy
    public void reset() {
        errorMSG = null;
    }

    public String getErrorMSG() {
        return errorMSG;
    }

    public void setErrorMSG(String errorMSG) {
        this.errorMSG = errorMSG;
    }

}

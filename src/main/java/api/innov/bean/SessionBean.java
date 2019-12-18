/**
 *
 */
package api.innov.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String pageName;
    private java.lang.String pageTitle;
    private java.lang.String pageCode = "LOGIN";
       
    private String errorMsgKey = "errMsg_GeneralError";
    private Locale locale = null;
    private String tameioID;

    private Object parameter;
    private List<Object> parameterList;
    private String alertMessage;
    private Boolean showGeneralDialog = false;
    TimeZone timeZone;
    
    private Boolean showMsgDialog = false;
    private String errorMsg = "";
    

    public String getPageTitle() {
        if (pageTitle == null) {
            //pageTitle = MessageBundleLoader.getMessage("loginPage");
        }
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

        
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public java.lang.String getPageName() {
        return pageName;
    }

    public void setPageName(java.lang.String pageName) {
        this.pageName = pageName;
    }


    public String getErrorMsgKey() {
        return errorMsgKey;
    }

    public void setErrorMsgKey(String errorMsgKey) {
        this.errorMsgKey = errorMsgKey;
    }

    public TimeZone getTimeZone() {
        timeZone = java.util.TimeZone.getDefault();
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

       
    
    public Boolean getShowMsgDialog() {
        return showMsgDialog;
    }

    public void setShowMsgDialog(Boolean showMsgDialog) {
        this.showMsgDialog = showMsgDialog;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    
    public void greekLocale(ActionEvent actionEvent) {
        setLocale(new Locale("gr"));
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("gr"));
    }

    public void englishLocale(ActionEvent actionEvent) {
        setLocale(Locale.ENGLISH);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
    }

    public String getTameioID() {
        return tameioID;
    }

    public void setTameioID(String tameioID) {
        this.tameioID = tameioID;
    }

    

    public java.lang.String getPageCode() {
        return pageCode;
    }

    public void setPageCode(java.lang.String pageCode) {
        this.pageCode = pageCode;
    }

    
    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public List<Object> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Object> parameterList) {
        this.parameterList = parameterList;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

   
    public Boolean getShowGeneralDialog() {
        return showGeneralDialog;
    }

    public void setShowGeneralDialog(Boolean showGeneralDialog) {
        this.showGeneralDialog = showGeneralDialog;
    }
}

package internal

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.main.TestCaseMain


/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */
public class GlobalVariable {
     
    /**
     * <p></p>
     */
    public static Object sleepSomeTime
     
    /**
     * <p></p>
     */
    public static Object smallSleepTime
     
    /**
     * <p></p>
     */
    public static Object isCapture
     
    /**
     * <p></p>
     */
    public static Object owner_mail
     
    /**
     * <p></p>
     */
    public static Object basicToken
     
    /**
     * <p></p>
     */
    public static Object owner_pass
     
    /**
     * <p></p>
     */
    public static Object myUrl
     
    /**
     * <p></p>
     */
    public static Object myApi
     
    /**
     * <p></p>
     */
    public static Object version
     
    /**
     * <p></p>
     */
    public static Object adminUrl
     
    /**
     * <p></p>
     */
    public static Object adminApiUrl
     
    /**
     * <p></p>
     */
    public static Object testOpsUrl
     
    /**
     * <p></p>
     */
    public static Object testOpsApiUrl
     
    /**
     * <p></p>
     */
    public static Object defaultOrg
     
    /**
     * <p></p>
     */
    public static Object turingAndGryffindoorTeamId
     
    /**
     * <p></p>
     */
    public static Object platformGeneralProject
     
    /**
     * <p></p>
     */
    public static Object platformGenaralProjectId
     
    /**
     * <p></p>
     */
    public static Object cloudianTeamId
     
    /**
     * <p></p>
     */
    public static Object cloudBasedExecProjectId
     
    /**
     * <p></p>
     */
    public static Object leopardTeamId
     
    /**
     * <p></p>
     */
    public static Object commerceEngineProject
     
    /**
     * <p></p>
     */
    public static Object commerceEngineProjectId
     
    /**
     * <p></p>
     */
    public static Object pegasusTeamId
     
    /**
     * <p></p>
     */
    public static Object accountAdminProject
     
    /**
     * <p></p>
     */
    public static Object accuntAdminProjectId
     
    /**
     * <p></p>
     */
    public static Object isAPIRunning
     
    /**
     * <p></p>
     */
    public static Object encodedToken
     
    /**
     * <p></p>
     */
    public static Object customFieldId
     
    /**
     * <p></p>
     */
    public static Object currentTestCaseName
     
    /**
     * <p></p>
     */
    public static Object vst_pro_email
     
    /**
     * <p></p>
     */
    public static Object vst_pro_password
     
    /**
     * <p></p>
     */
    public static Object ownerAbbrName
     
    /**
     * <p></p>
     */
    public static Object isNewUIScheduleEnabled
     
    /**
     * <p></p>
     */
    public static Object testCloudUrl
     
    /**
     * <p></p>
     */
    public static Object turingProjectId
     
    /**
     * <p></p>
     */
    public static Object default_projectId
     
    /**
     * <p></p>
     */
    public static Object default_org
     
    /**
     * <p></p>
     */
    public static Object default_team
     
    /**
     * <p></p>
     */
    public static Object projectId
     
    /**
     * <p></p>
     */
    public static Object accountAdminProjectId
     
    /**
     * <p></p>
     */
    public static Object visualTestUrl
     

    static {
        try {
            def selectedVariables = TestCaseMain.getGlobalVariables("default")
			selectedVariables += TestCaseMain.getGlobalVariables(RunConfiguration.getExecutionProfile())
            selectedVariables += TestCaseMain.getParsedValues(RunConfiguration.getOverridingParameters())
    
            sleepSomeTime = selectedVariables['sleepSomeTime']
            smallSleepTime = selectedVariables['smallSleepTime']
            isCapture = selectedVariables['isCapture']
            owner_mail = selectedVariables['owner_mail']
            basicToken = selectedVariables['basicToken']
            owner_pass = selectedVariables['owner_pass']
            myUrl = selectedVariables['myUrl']
            myApi = selectedVariables['myApi']
            version = selectedVariables['version']
            adminUrl = selectedVariables['adminUrl']
            adminApiUrl = selectedVariables['adminApiUrl']
            testOpsUrl = selectedVariables['testOpsUrl']
            testOpsApiUrl = selectedVariables['testOpsApiUrl']
            defaultOrg = selectedVariables['defaultOrg']
            turingAndGryffindoorTeamId = selectedVariables['turingAndGryffindoorTeamId']
            platformGeneralProject = selectedVariables['platformGeneralProject']
            platformGenaralProjectId = selectedVariables['platformGenaralProjectId']
            cloudianTeamId = selectedVariables['cloudianTeamId']
            cloudBasedExecProjectId = selectedVariables['cloudBasedExecProjectId']
            leopardTeamId = selectedVariables['leopardTeamId']
            commerceEngineProject = selectedVariables['commerceEngineProject']
            commerceEngineProjectId = selectedVariables['commerceEngineProjectId']
            pegasusTeamId = selectedVariables['pegasusTeamId']
            accountAdminProject = selectedVariables['accountAdminProject']
            accuntAdminProjectId = selectedVariables['accuntAdminProjectId']
            isAPIRunning = selectedVariables['isAPIRunning']
            encodedToken = selectedVariables['encodedToken']
            customFieldId = selectedVariables['customFieldId']
            currentTestCaseName = selectedVariables['currentTestCaseName']
            vst_pro_email = selectedVariables['vst_pro_email']
            vst_pro_password = selectedVariables['vst_pro_password']
            ownerAbbrName = selectedVariables['ownerAbbrName']
            isNewUIScheduleEnabled = selectedVariables['isNewUIScheduleEnabled']
            testCloudUrl = selectedVariables['testCloudUrl']
            turingProjectId = selectedVariables['turingProjectId']
            default_projectId = selectedVariables['default_projectId']
            default_org = selectedVariables['default_org']
            default_team = selectedVariables['default_team']
            projectId = selectedVariables['projectId']
            accountAdminProjectId = selectedVariables['accountAdminProjectId']
            visualTestUrl = selectedVariables['visualTestUrl']
            
        } catch (Exception e) {
            TestCaseMain.logGlobalVariableError(e)
        }
    }
}

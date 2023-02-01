package katalon.services

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import katalon.fw.lib.BaseService
import katalon.model.Device

public class ExecutionEnvService extends BaseService<ExecutionEnvService> {
	public List<Device> getResponseBody() {
		return parseResponseBodyToClassObject(Device[].class)
	}
	
	public ExecutionEnvService compareAllDevicesListToOnlyAvailableList(List<Device> allDevices, List<Device> onlyAvailableDevices) {
		// 30 is just an average estimation by observing the results.
		WS.verifyGreaterThanOrEqual(allDevices.size() - onlyAvailableDevices.size(), 30)
		return this
	}
}

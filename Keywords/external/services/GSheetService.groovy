package external.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.GSheetValueRange

public class GSheetService extends BaseService<GSheetService> {
	public GSheetService getToken () {
		String token = parseResponseBodyToJsonObject().access_token
		GlobalVariable.gshToken = token
		return this
	}

	public GSheetValueRange getGetRowsResponseBody() {
		return parseResponseBodyToClassObject(GSheetValueRange.class)
	}
}

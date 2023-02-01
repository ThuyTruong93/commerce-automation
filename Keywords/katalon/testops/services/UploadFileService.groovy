package katalon.testops.services

import katalon.fw.lib.BaseService
import katalon.fw.lib.Page

public class UploadFileService extends BaseService<UploadFileService> {
	public UploadFileService uploadFileToS3(String uploadUrl, String filePath) {
		initRequestObject()
				.setUrl(uploadUrl)
				.withFileBodyContent(filePath)
				.sendPutRequest()
		return this
	}
}

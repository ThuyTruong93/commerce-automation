package katalon.enums

public enum Subscriptions {

	KSE_PERUSER_ANNUAL("kse_per-user_annual"),
	KSE_PERUSER_MONTHLY("kse_per-user_monthly"),
	KRE_FLOATING_ANNUAL("kre_floating_annual"),
	KRE_FlOATING_MONTHLY("kre_floating_monthly"),
	PLATFORM_ANNUAL("testops_platform_annual"),
	PLATFORM_MONTHLY("testops_platform_monthly"),
	TESTCLOUD_MONTHLY("testcloud_web_sessions_monthly"),
	TESTCLOUD_ANNUAL("testcloud_web_sessions_annual"),
	VISUAL_TESTING_MONTHLY("visual_testing_checkpoints_monthly"),
	VISUAL_TESTING_ANNUAL("visual_testing_checkpoints_annual")
	
	String code

	Subscriptions(String code) {
		this.code = code
	}

	public String getCode() {
		return code;
	}
}

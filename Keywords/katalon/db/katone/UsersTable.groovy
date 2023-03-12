package katalon.db.katone

import katalon.fw.db.PostgreSql
import katalon.fw.lib.BaseTable
import katalon.fw.lib.Page

public class UsersTable extends BaseTable<UsersTable> {

	private UsersTable() {
		dbName = "katone"
		table = "auth.users"
	}

	public UsersTable insertUser(String email, String sqlPassword, String firstName, String lastName) {

		def insertScript = "INSERT INTO auth.users\
				(id, archived, createdat, email, firstname, jobtitle, lastname, password, resetpasswordcode, resetpasswordcodecreatedat, status, updatedat, avatar, lastlogin)\
				VALUES(nextval('auth.users_id_seq'), false, NOW(), '$email', '$firstName', 'Manual Tester',  '$lastName', '$sqlPassword', NULL, NULL, 'VERIFIED', NOW(), NULL, NOW());";
		execute(insertScript)

		return this
	}

	public String selectUserId(String email) {
		return select("email = '$email'", "id")
	}

	public UsersTable deleteUser(String email) {
		def deleteScript = "UPDATE auth.users\
							SET archived=true, deletereason='Remove Automation Test Account'\
							WHERE email='$email' AND archived = false;"

		execute(deleteScript)
		return this
	}
}

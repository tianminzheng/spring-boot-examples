package com.springboot.jdbc.template;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementCallback {
	
	Object handleStatement(Statement statement) throws SQLException;  
}

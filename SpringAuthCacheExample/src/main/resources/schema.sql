-- 位于 org.springframework.security.core.userdetails.jdbc包中
CREATE TABLE IF NOT EXISTS users(
		username varchar_ignorecase(50) NOT NULL PRIMARY KEY,
		password VARCHAR_IGNORECASE(500) NOT NULL,
		enabled BOOLEAN NOT NULL);
CREATE TABLE IF NOT EXISTS authorities(
		username varchar_ignorecase(50) NOT NULL,
		authority varchar_ignorecase(50) NOT NULL,
		CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username));

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON authorities (username,authority);
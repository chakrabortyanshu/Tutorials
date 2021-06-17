--liquibase formatted sql

--changeset rl185192:abcd-151-2
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(1) FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='message_direction_indicator';
INSERT INTO PROPERTY(PTY_PTS_NAME, PTY_NAME, PTY_VALUE, PTY_DESC, PTY_LAST_UPDATE_TS) VALUES ('abcd_Properties', 'message_direction_indicator', 'DS', 'message_direction_indicator', CURRENT_TIMESTAMP);
--rollback DELETE FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='message_direction_indicator';

--changeset rl185192:abcd-151-3
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(1) FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='live_test_indicator';
INSERT INTO PROPERTY(PTY_PTS_NAME, PTY_NAME, PTY_VALUE, PTY_DESC, PTY_LAST_UPDATE_TS) VALUES ('abcd_Properties', 'live_test_indicator', 'T', 'live_test_indicator', CURRENT_TIMESTAMP);
--rollback DELETE FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='live_test_indicator';

--changeset rl185192:abcd-151-4
--preconditions onFail:MARK_RAN
--precondition-sql-check expectedResult:0 SELECT COUNT(1) FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='implementation_version_number';
INSERT INTO PROPERTY(PTY_PTS_NAME, PTY_NAME, PTY_VALUE, PTY_DESC, PTY_LAST_UPDATE_TS) VALUES ('abcd_Properties', 'implementation_version_number', '100', 'implementation_version_number', CURRENT_TIMESTAMP);
--rollback DELETE FROM PROPERTY WHERE PTY_PTS_NAME='abcd_Properties' and PTY_NAME='implementation_version_number';

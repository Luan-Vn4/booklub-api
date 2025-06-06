CREATE OR REPLACE FUNCTION delete_activity()
RETURNS TRIGGER AS $$
    BEGIN
        DELETE FROM activities WHERE id = OLD.id;
        RETURN OLD;
    END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trigger_delete_club_activity ON club_activities;
CREATE TRIGGER trigger_delete_club_activity
    AFTER DELETE ON club_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

DROP TRIGGER IF EXISTS trigger_delete_user_activity ON user_activities;
CREATE TRIGGER trigger_delete_user_activity
    AFTER DELETE ON user_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

DROP TRIGGER IF EXISTS trigger_delete_meeting_defined_activity ON meeting_defined_activities;
CREATE TRIGGER  trigger_delete_meeting_defined_activity
    AFTER DELETE ON meeting_defined_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

DROP TRIGGER IF EXISTS trigger_delete_member_completed_reading_activity ON member_completed_reading_activities;
CREATE TRIGGER trigger_delete_member_completed_reading_activity
    AFTER DELETE ON member_completed_reading_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

DROP TRIGGER IF EXISTS trigger_delete_reading_goal_defined_activity ON reading_goal_defined_activities;
CREATE TRIGGER trigger_delete_reading_goal_defined_activity
    AFTER DELETE ON reading_goal_defined_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

DROP TRIGGER IF EXISTS trigger_delete_user_completed_reading_activity ON user_completed_reading_activities;
CREATE TRIGGER trigger_delete_user_completed_reading_activity
    AFTER DELETE ON user_completed_reading_activities
    FOR EACH ROW
    EXECUTE FUNCTION delete_activity();

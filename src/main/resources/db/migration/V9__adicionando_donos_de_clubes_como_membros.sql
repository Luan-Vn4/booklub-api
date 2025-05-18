INSERT INTO clubs_users (club_id, user_id) (
    SELECT clubs.id, clubs.owner_id
        FROM clubs
        WHERE NOT EXISTS (
            SELECT 1
                FROM clubs_users
                WHERE clubs_users.club_id=clubs.id
                AND clubs_users.user_id=clubs.owner_id
        )
);
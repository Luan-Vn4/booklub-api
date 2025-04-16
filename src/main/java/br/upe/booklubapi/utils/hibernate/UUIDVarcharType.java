package br.upe.booklubapi.utils.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

public class UUIDVarcharType implements EnhancedUserType<UUID> {

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<UUID> returnedClass() {
        return UUID.class;
    }

    @Override
    public boolean equals(UUID x, UUID y) {
        return x.equals(y);
    }

    @Override
    public int hashCode(UUID x) {
        return x.hashCode();
    }

    @Override
    public UUID nullSafeGet(
        ResultSet rs,
        int position,
        SharedSessionContractImplementor session,
        Object owner
    ) throws SQLException {
        String uuid = rs.getString(position);
        return UUID.fromString(uuid);
    }

    @Override
    public void nullSafeSet(
        PreparedStatement st,
        UUID value,
        int index,
        SharedSessionContractImplementor session
    ) throws SQLException {
        st.setString(index, value != null ? value.toString() : null);
    }

    @Override
    public UUID deepCopy(UUID value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(UUID value) {
        return value;
    }

    @Override
    public UUID assemble(Serializable cached, Object owner) {
        return cached != null ? (UUID) cached : null;
    }

    @Override
    public String toSqlLiteral(UUID value) {
        return value != null ? "'" + value + "'" : "NULL";
    }

    @Override
    public String toString(UUID value) throws HibernateException {
        return value.toString();
    }

    @Override
    public UUID fromStringValue(CharSequence sequence) throws HibernateException {
        return UUID.fromString(sequence.toString());
    }

}

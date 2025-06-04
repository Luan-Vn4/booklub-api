package br.upe.booklubapi.utils.hibernate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;
import org.locationtech.jts.io.WKBWriter;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

/**
 * @author Arjen10
 * @date 2023/5/29 下午7:48
 */
@Slf4j
public class GeometryType implements UserType<Geometry> {

    @Override
    public int getSqlType() {
        return Types.BINARY;
    }

    @Override
    public Class<Geometry> returnedClass() {
        return Geometry.class;
    }

    @Override
    public boolean equals(Geometry o,Geometry o1) throws HibernateException {
        return Objects.equals(o, o1);
    }

    @Override
    public int hashCode(Geometry o) throws HibernateException {
        return Objects.hashCode(o);
    }

    @Override
    public Geometry nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException   {
        if (rs.wasNull()) {
            return null;
        }
        try {
            String string = rs.getString(position);
            if (string == null) {
                return null;
            }
            final byte[] bytes = WKBReader.hexToBytes(string);
            return new WKBReader().read(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Geometry geometry, int i, SharedSessionContractImplementor sc) throws HibernateException, SQLException {
        if (Objects.isNull(geometry)) {
            ps.setNull(i, Types.BINARY);
            return;
        }
        byte[] write = new WKBWriter().write(geometry);
        try (InputStream is = new ByteArrayInputStream(write)) {
            ps.setBinaryStream(i, is);
        } catch (IOException | NullPointerException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Geometry deepCopy(Geometry geometry) throws HibernateException {
        if (Objects.isNull(geometry)) {
            return null;
        }
        return geometry.copy();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Geometry o) throws HibernateException {
        return o;
    }

    @Override
    public Geometry assemble(Serializable serializable, Object o) throws HibernateException {
        return deepCopy((Geometry) serializable);
    }

    @Override
    public Geometry replace(Geometry original, Geometry o1, Object o2) throws HibernateException {
        return deepCopy(original);
    }

}

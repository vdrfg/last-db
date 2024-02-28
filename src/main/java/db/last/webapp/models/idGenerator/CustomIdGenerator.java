package db.last.webapp.models.idGenerator;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.*;

@AllArgsConstructor
public class CustomIdGenerator implements IdentifierGenerator {

  private String prefix;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    Connection connection = (Connection) session.getJdbcConnectionAccess();

    try {
      PreparedStatement ps =
          connection.prepareStatement(
              "select max(cast(substring("
                  + prefix.toLowerCase()
                  + "_id, ?) as unsigned)) as max_id from "
                  + prefix);

        ps.setInt(1, prefix.length() + 1);

        ResultSet rs = ps.executeQuery();

      if (rs.next()) {
        long maxId = rs.getLong("max_id");
        return prefix + (maxId + 1);
      }

      rs.close();
      connection.close();

    } catch (SQLException e) {
      // TODO: add specific exception
      e.printStackTrace();
    }

    return null;
  }
}

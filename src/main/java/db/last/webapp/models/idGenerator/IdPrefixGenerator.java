package db.last.webapp.models.idGenerator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class IdPrefixGenerator extends SequenceStyleGenerator {

  public static final String PREFIX_VALUE_PARAMETER = "prefixValue";
  public static final String PREFIX_VALUE_DEFAULT = "";
  private String prefixValue;

  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    return prefixValue + String.format(super.generate(session, object).toString());
  }

  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {
    super.configure(type, params, serviceRegistry);
    prefixValue =
        ConfigurationHelper.getString(PREFIX_VALUE_PARAMETER, params, PREFIX_VALUE_DEFAULT);
  }
}

package db.last.webapp.models.idGenerator;

import com.sun.jdi.LongType;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

import java.io.Serializable;
import java.util.Properties;

public class IdPrefixGenerator extends SequenceStyleGenerator {

  public static final String PREFIX_VALUE_PARAMETER = "prefixValue";
  public static final String PREFIX_VALUE_DEFAULT = "";
  private String prefixValue;
  private String numberFormat;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {
    return prefixValue + String.format(numberFormat, super.generate(session, object));
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {
    super.configure(
        new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class),
        params,
        serviceRegistry);
    prefixValue =
        ConfigurationHelper.getString(PREFIX_VALUE_PARAMETER, params, PREFIX_VALUE_DEFAULT);
    numberFormat = ConfigurationHelper.getString("numberFormat", params, "%d");
  }
}

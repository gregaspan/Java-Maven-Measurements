package si.um.feri.measurements.dao;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import si.um.feri.measurements.vao.Measurement;
import si.um.feri.measurements.vao.Product;
import java.util.Date;
import java.util.List;

@Stateless
@Local(MeasurementDao.class)
public class MeasurementDaoBean implements MeasurementDao {

    @PersistenceContext
    EntityManager em;

    @EJB
    ProductDao productDao;

    @Resource(lookup = "java:jboss/mail/MojMail")
    private Session mailSession;

    private static final String NOTIFICATION_EMAIL = "tmeasurements31@gmail.com";

    @Override
    public Measurement save(Product p, double currentMeasurement) {
        Product product = productDao.find(p.getId());
        if (product == null) return null;

        Measurement m = new Measurement();
        m.setProduct(product);
        m.setValue(currentMeasurement);
        boolean isMeasurementOk = product.getMinMeasure() <= currentMeasurement && currentMeasurement <= product.getMaxMeasure();
        m.setOk(isMeasurementOk);
        em.persist(m);

        if (!isMeasurementOk) {
            sendNotificationEmail(product, currentMeasurement);
        }

        return m;
    }

    private void sendNotificationEmail(Product product, double measurement) {
        try {
            Message message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(NOTIFICATION_EMAIL));
            message.setSubject("🚨Measurement Notification");
            String emailBody = String.format("Measurement for " + product.getName() + " is out of tolerance." +
                            "\n\nTemperature should be between:" + product.getMinMeasure() + " and " + product.getMaxMeasure() +
                            "\n\nMeasurement taken: " + measurement +
                            "\n\nTime of measurement: " + new Date());
            message.setText(emailBody);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Measurement save(Measurement m) {
        em.persist(m);
        return m;
    }

    @Override
    public List<Measurement> getAllForProduct(Product p) {
        Query q = em.createQuery("select m from Measurement m where m.product.id = :id order by m.created desc");
        q.setParameter("id", p.getId());
        return q.getResultList();
    }
}

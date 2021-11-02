package hibAssignment;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemDao {
	public void saveItem(Item item)
	{
		Transaction trans = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			trans = session.beginTransaction();
			session.save(item);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void removeItem(Item item)
	{
		Transaction trans = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			trans = session.beginTransaction();
			session.remove(item);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void makeDone(Item item)
	{
		Transaction trans = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			trans = session.beginTransaction();
			session.remove(item);
			item.SetDoneness(item, true);
			session.save(item);
			trans.commit();
		} catch (Exception e) {
			if (trans != null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public List<Item> getItems()
	{
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Item> items = session.createQuery("from Item", Item.class).list();

			return items;
		}
	}
}
